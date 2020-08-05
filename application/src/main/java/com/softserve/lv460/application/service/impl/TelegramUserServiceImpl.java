package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.exception.exceptions.TelegramUserAlreadyRegisterException;
import com.softserve.lv460.application.exception.exceptions.TelegramUserNotFound;
import com.softserve.lv460.application.repository.TelegramUserRepository;
import com.softserve.lv460.application.service.TelegramUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository telegramUserRepository;

    public TelegramUser create(TelegramUser telegramUser) {
        if (!telegramUserRepository.findByUsername(telegramUser.getUsername()).isPresent()) {
            return telegramUserRepository.save(telegramUser);
        }
        throw new TelegramUserAlreadyRegisterException(String.format(ErrorMessage.TELEGRAM_ALREADY_REGISTER, telegramUser.getUsername()));
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
        return telegramUserRepository.findByUsername(username)
                .orElseThrow(() -> new TelegramUserNotFound(String.format(ErrorMessage.TELEGRAM_NOT_FOUND_BY_USERNAME, username)));
    }
}
