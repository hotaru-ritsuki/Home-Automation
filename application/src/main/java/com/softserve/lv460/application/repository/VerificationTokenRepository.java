package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
 Optional<VerificationToken> findByToken(String verificationToken);
 Optional<VerificationToken> findByUserId(Long userId);
 Optional<VerificationToken> findByUserIdAndToken(Long userId, String token);
 int deleteAllByExpiryDateIsBefore(LocalDateTime localDateTime);
}
