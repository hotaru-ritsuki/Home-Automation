package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.TelegramActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface TelegramActivationRepository extends JpaRepository<TelegramActivation,Long> {
  boolean existsByTelegramUserId(Long telegramUserId);
  Optional<TelegramActivation> findByTelegramUserId(Long telegramUserId);
  @Transactional
  int deleteByTelegramUserId(Long telegramUserId);
  @Transactional
  int deleteAllByExpiryDateIsBefore(LocalDateTime localDateTime);
}
