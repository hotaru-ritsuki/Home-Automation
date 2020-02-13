package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.exception.exceptions.TokenNotValidException;
import com.softserve.lv460.application.exception.exceptions.UserActivationEmailTokenExpiredException;
import com.softserve.lv460.application.exception.exceptions.UserAlreadyActivated;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.softserve.lv460.application.constant.ErrorMessage.*;
import static com.softserve.lv460.application.constant.ErrorMessage.USER_ALREADY_ACTIVATED;

@Service
@AllArgsConstructor
public class VerificationTokenService {
    private VerificationTokenRepository tokenRepository;
    private ApplicationUserRepository userRepository;

    public VerificationToken findByUserIdAndToken(Long id, String token) {
        return tokenRepository.findByUserIdAndToken(id, token)
                .orElseThrow(() -> new UserActivationEmailTokenExpiredException(ErrorMessage.VERIFICATION_TOKEN_IS_NOT_VALID));
    }

    public void delete(Long id) {
        if (tokenRepository.findById(id).isEmpty()) {
            throw new NotDeletedException(ErrorMessage.VERIFICATION_TOKEN_IS_EXPIRED);
        }
        tokenRepository.deleteById(id);
    }

    public VerificationToken createVerificationTokenForUser(ApplicationUser user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
        return myToken;
    }

    public void validateVerificationToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotValidException(VERIFICATION_TOKEN_IS_NOT_VALID));
      if (verificationToken.getExpiryDate().toLocalDate().isBefore(LocalDate.now()) || verificationToken.getExpiryDate().toLocalDate().isEqual(LocalDate.now()) ) {
            tokenRepository.delete(verificationToken);
            throw new TokenNotValidException(VERIFICATION_TOKEN_IS_EXPIRED);
        }
        ApplicationUser applicationUser = verificationToken.getUser();
        applicationUser.setEnabled(true);
        userRepository.save(applicationUser);
        tokenRepository.delete(verificationToken);
    }

    public VerificationToken generateNewVerificationToken(String email) throws UserAlreadyActivated {
        ApplicationUser applicationUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
        if (applicationUser.isEnabled()) {
            throw new UserAlreadyActivated(USER_ALREADY_ACTIVATED);
        }
        if (tokenRepository.findByUserId(applicationUser.getId()).isPresent()) {
            tokenRepository.delete(tokenRepository.findByUserId(applicationUser.getId()).get());
        }
        VerificationToken verificationToken = createVerificationTokenForUser(applicationUser, UUID.randomUUID().toString());
        tokenRepository.save(verificationToken);
        return verificationToken;
    }
}
