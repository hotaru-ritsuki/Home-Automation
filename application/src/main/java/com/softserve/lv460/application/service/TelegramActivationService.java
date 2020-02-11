package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.TelegramActivation;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.exception.exceptions.NotFoundException;
import com.softserve.lv460.application.exception.exceptions.TokenNotValidException;
import com.softserve.lv460.application.repository.TelegramActivationRepository;
import com.softserve.lv460.application.repository.TelegramUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.softserve.lv460.application.constant.ErrorMessage.VERIFICATION_TOKEN_IS_EXPIRED;

@Slf4j
@Service
@AllArgsConstructor
public class TelegramActivationService {
  private TelegramActivationRepository telegramActivationRepository;
  private TelegramUserRepository telegramUserRepository;

  public void save(TelegramActivation telegramActivation) {
    telegramActivationRepository.save(telegramActivation);
  }

  public boolean existsById(Long id) {
    return telegramActivationRepository.existsById(id);
  }

  public boolean existsByTelegramId(Long telegramId) {
    return telegramActivationRepository.existsByTelegramUserId(telegramId);
  }

  public boolean existsByTelegramUsername(String username){
    TelegramUser telegramUser=telegramUserRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Not found"));
    return telegramActivationRepository.existsByTelegramUserId(telegramUser.getId());
  }

  public List<TelegramActivation> findAll() {
    return telegramActivationRepository.findAll();
  }

  public void deleteById(Long id) {
    if (!telegramActivationRepository.findById(id).isPresent()) {
      throw new NotDeletedException(String.format(ErrorMessage.TELEGRAM_CODE_NOT_DELETED_BY_ID, id));
    }
    telegramActivationRepository.deleteById(id);
  }

  public void deleteByTelegramUserId(Long telegramUserId) {
    if (!telegramActivationRepository.findByTelegramUserId(telegramUserId).isPresent()) {
      throw new NotDeletedException(String.format(ErrorMessage.TELEGRAM_CODE_NOT_DELETED_BY_ID, telegramUserId));
    }
    telegramActivationRepository.deleteByTelegramUserId(telegramUserId);
  }

  public TelegramActivation findByUsername(String username) {
    TelegramUser telegramUser = telegramUserRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.TELEGRAM_NOT_DELETED_BY_USERNAME, username)));
    return telegramActivationRepository.findById(telegramUser.getId())
            .orElseThrow(() -> new IllegalArgumentException(String.format(ErrorMessage.TELEGRAM_CODE_NOT_FOUND_BY_USERNAME, username)));
  }

  public boolean validate(String telegramUsername, String token) {
    TelegramActivation telegramActivation = findByUsername(telegramUsername);
    if (telegramActivation.getExpiryDate().isAfter(LocalDateTime.now())) {
      telegramActivationRepository.delete(telegramActivation);
      return false;
    }
    TelegramUser telegramUser = telegramActivation.getTelegramUser();
    telegramUser.setEnabled(true);
    telegramUserRepository.save(telegramUser);
    telegramActivationRepository.delete(telegramActivation);
    return true;
  }

  @Scheduled(fixedRate = 86400000)
  public void deleteExpired() {
    int rows = telegramActivationRepository.deleteAllByExpiryDateIsBefore(LocalDateTime.now());
    log.info(rows + " telegram verification tokens were deleted.");
  }
}
