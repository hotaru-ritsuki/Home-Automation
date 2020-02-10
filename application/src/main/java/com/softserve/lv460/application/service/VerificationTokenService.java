package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.exception.exceptions.UserActivationEmailTokenExpiredException;
import com.softserve.lv460.application.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerificationTokenService {
    private VerificationTokenRepository tokenRepository;

    public VerificationToken findByUserIdAndToken(Long id, String token) {
        return tokenRepository.findByUserIdAndToken(id, token)
                .orElseThrow(() -> new UserActivationEmailTokenExpiredException(ErrorMessage.VERIFICATION_TOKEN_IS_NOT_VALID));
    }
}
