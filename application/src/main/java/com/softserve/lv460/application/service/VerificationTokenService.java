package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.exception.exceptions.UserActivationEmailTokenExpiredException;
import com.softserve.lv460.application.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class VerificationTokenService {
    private VerificationTokenRepository tokenRepository;

    public VerificationToken findByUserIdAndToken(Long id, String token) {
        return tokenRepository.findByUserIdAndToken(id, token)
                .orElseThrow(() -> new UserActivationEmailTokenExpiredException(ErrorMessage.VERIFICATION_TOKEN_IS_NOT_VALID));
    }

    @Scheduled(fixedRate = 86400000)
    public void deleteExpired() {
        int rows = tokenRepository.deleteAllByExpiryDateIsBefore(LocalDateTime.now());
        log.info(rows + " telegram verification tokens were deleted.");
    }
}
