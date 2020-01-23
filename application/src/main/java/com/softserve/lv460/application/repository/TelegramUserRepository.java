package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
  TelegramUser findByUsername(String username);
}
