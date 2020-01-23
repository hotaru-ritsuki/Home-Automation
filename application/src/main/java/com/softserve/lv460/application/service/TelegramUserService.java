package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.repository.TelegramUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TelegramUserService {
  private TelegramUserRepository telegramUserRepository;

  public TelegramUser create(TelegramUser telegramUser) {
    return telegramUserRepository.save(telegramUser);
  }

  public List<TelegramUser> findAll() {
    return telegramUserRepository.findAll();
  }

  public void delete(Long id) {
    if (!telegramUserRepository.findById(id).isPresent()) {
      throw new NotDeletedException(String.format(ErrorMessage.TELEGRAM_NOT_DELETED_BY_ID, id));
    }
    telegramUserRepository.deleteById(id);
  }

  public TelegramUser findByUsername(String username) {
    return telegramUserRepository.findByUsername(username);
  }
}
