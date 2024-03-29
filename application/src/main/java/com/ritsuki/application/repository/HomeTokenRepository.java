package com.ritsuki.application.repository;

import com.ritsuki.application.entity.HomeToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface HomeTokenRepository extends JpaRepository<HomeToken, Long> {
  Optional<HomeToken> findById(Long id);
  Optional<HomeToken> findByToken(String token);
  Optional<HomeToken> findByHome(Long homeId);
  int deleteAllByExpiryDateIsBefore(LocalDateTime date);
}
