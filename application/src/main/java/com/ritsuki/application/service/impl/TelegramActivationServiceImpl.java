package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.entity.TelegramActivation;
import com.ritsuki.application.entity.TelegramUser;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.exception.exceptions.NotFoundException;
import com.ritsuki.application.repository.TelegramActivationRepository;
import com.ritsuki.application.repository.TelegramUserRepository;
import com.ritsuki.application.service.TelegramActivationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class TelegramActivationServiceImpl implements TelegramActivationService {
    private final TelegramActivationRepository telegramActivationRepository;
    private final TelegramUserRepository telegramUserRepository;

    public String save(TelegramActivation telegramActivation) {
        telegramActivationRepository.save(telegramActivation);
        return telegramActivation.getToken();
    }

    public String save(TelegramUser telegramUser) {
        TelegramActivation activation = new TelegramActivation();
        activation.setToken(String.format("%05d", new SecureRandom().nextInt(1000000)));
        activation.setTelegramUser(telegramUser);
        return save(activation);
    }

    public boolean existsById(Long id) {
        return telegramActivationRepository.existsById(id);
    }

    public boolean existsByTelegramUserId(Long telegramUserId) {
        return telegramActivationRepository.existsByTelegramUserId(telegramUserId);
    }

    public boolean existsByTelegramUsername(String username) {
        TelegramUser telegramUser = telegramUserRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not found"));
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
        return telegramActivationRepository.findByTelegramUserId(telegramUser.getId())
                .orElseThrow(() -> new IllegalArgumentException(String.format(ErrorMessage.TELEGRAM_CODE_NOT_FOUND_BY_USERNAME, username)));
    }

    public boolean validate(String telegramUsername, String token) {
        TelegramActivation telegramActivation = findByUsername(telegramUsername);
        if (telegramActivation.getExpiryDate().isBefore(LocalDateTime.now()) || !(telegramActivation.getToken().equals(token))) {
            telegramActivationRepository.delete(telegramActivation);
            return false;
        }
        TelegramUser telegramUser = telegramActivation.getTelegramUser();
        telegramUser.setEnabled(true);
        telegramUserRepository.save(telegramUser);
        telegramActivationRepository.delete(telegramActivation);
        return true;
    }

    public void deleteExpired() {
        int rows = telegramActivationRepository.deleteAllByExpiryDateIsBefore(LocalDateTime.now());
        log.info(rows + " telegram verification tokens were deleted.");
    }
}
