package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.TelegramActivation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TelegramActivationRepository extends JpaRepository<TelegramActivation,Long> {
  boolean existsByTelegramUserId(Long telegramUserId);
  Optional<TelegramActivation> findByTelegramUserId(Long telegramUserId);
  boolean deleteByTelegramUserId(Long telegramUserId);
  int deleteAllByExpiryDateIsBefore(LocalDateTime localDateTime);
}
