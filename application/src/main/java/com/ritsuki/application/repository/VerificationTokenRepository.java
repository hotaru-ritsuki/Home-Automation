package com.ritsuki.application.repository;

import com.ritsuki.application.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
 Optional<VerificationToken> findByToken(String verificationToken);
 Optional<VerificationToken> findByUserId(Long userId);
 Optional<VerificationToken> findByUserIdAndToken(Long userId, String token);
}
