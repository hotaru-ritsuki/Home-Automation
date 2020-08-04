package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.TelegramActivation;
import com.softserve.lv460.application.entity.TelegramUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TelegramActivationService {

    String save(TelegramActivation telegramActivation);

    String save(TelegramUser telegramUser);

    boolean existsById(Long id);

    boolean existsByTelegramUserId(Long telegramUserId);

    boolean existsByTelegramUsername(String username);

    List<TelegramActivation> findAll();

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByTelegramUserId(Long telegramUserId);

    TelegramActivation findByUsername(String username);

    boolean validate(String telegramUsername, String token);

    @Scheduled(fixedRate = 86400000)
    void deleteExpired();
}
