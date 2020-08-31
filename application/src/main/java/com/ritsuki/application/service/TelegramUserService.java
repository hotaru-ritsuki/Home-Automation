package com.ritsuki.application.service;

import com.ritsuki.application.entity.TelegramUser;

import java.util.List;

public interface TelegramUserService {

    TelegramUser create(TelegramUser telegramUser);

    List<TelegramUser> findAll();

    void delete(Long id);

    TelegramUser findByUsername(String username);
}
