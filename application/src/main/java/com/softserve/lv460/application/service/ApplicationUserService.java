package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.exception.exceptions.*;
import com.softserve.lv460.application.mapper.user.UserRegistrationRequestMapper;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.repository.VerificationTokenRepository;
import com.softserve.lv460.application.security.dto.JWTSuccessLogIn;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.JWTUserResponse;
import com.softserve.lv460.application.security.dto.UserRegistrationRequest;
import com.softserve.lv460.application.security.jwt.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.softserve.lv460.application.constant.ErrorMessage.*;

@Slf4j
@AllArgsConstructor
@Service
public class ApplicationUserService {
  private final ApplicationUserRepository applicationUserRepository;
  private final UserRegistrationRequestMapper userRegistrationRequestMapper;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;
  private final VerificationTokenRepository verificationTokenRepository;

  public ApplicationUser save(UserRegistrationRequest userRequest) {
    if (applicationUserRepository.existsByEmail(userRequest.getEmail())) {
      throw new UserAlreadyRegisteredException(String.format(USER_ALREADY_EXISTS, userRequest.getEmail()));
    }
    ApplicationUser applicationUser = userRegistrationRequestMapper.toEntity(userRequest);
    applicationUserRepository.save(applicationUser);
    return applicationUser;
  }

  public void save(ApplicationUser applicationUser) {
    applicationUserRepository.save(applicationUser);
  }

  @Transactional
  public JWTUserResponse updateAccessTokens(String refreshToken) {
    String email;
    try {
      email = jwtTokenProvider.getEmailFromJWT(refreshToken);
    } catch (ExpiredJwtException e) {
      throw new BadRefreshTokenException(REFRESH_TOKEN_NOT_VALID);
    }
    ApplicationUser user = applicationUserRepository
            .findByEmail(email)
            .orElseThrow(() -> new BadEmailException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
    String newRefreshTokenKey = jwtTokenProvider.generateRefreshToken(email);
    applicationUserRepository.updateSecret(newRefreshTokenKey, user.getId());
    if (jwtTokenProvider.isTokenValid(refreshToken, user.getSecret())) {
      return new JWTUserResponse(
              jwtTokenProvider.generateAccessToken(email),
              jwtTokenProvider.generateRefreshToken(email)
      );
    }
    throw new BadRefreshTokenException(REFRESH_TOKEN_NOT_VALID);
  }

  public JWTSuccessLogIn login(JWTUserRequest loginRequest, Authentication auth) {
    ApplicationUser user = applicationUserRepository
            .findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new BadEmailException(String.format(USER_NOT_FOUND_BY_EMAIL, loginRequest.getEmail())));
    return new JWTSuccessLogIn(user.getId(), jwtTokenProvider.generateAccessToken(auth), jwtTokenProvider.generateRefreshToken(auth), user.getFirstName());
  }

  public void changeUserPassword(Long userId, String password) {
    ApplicationUser applicationUser = applicationUserRepository.findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, userId)));
    applicationUser.setPassword(passwordEncoder.encode(password));
    applicationUserRepository.save(applicationUser);
  }

  public boolean checkIfValidOldPassword(String newPassword, String oldPassword) {
    return passwordEncoder.matches(oldPassword, newPassword);
  }

  public ApplicationUser findByEmail(String email) {
    return applicationUserRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
  }

  public VerificationToken createVerificationTokenForUser(ApplicationUser user, String token) {
    VerificationToken myToken = new VerificationToken(token, user);
    verificationTokenRepository.save(myToken);
    return myToken;
  }

  public void validateVerificationToken(String token) {
    VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() -> new TokenNotValidException(VERIFICATION_TOKEN_IS_NOT_VALID));
    if (verificationToken.getExpiryDate().isAfter(LocalDateTime.now())) {
      verificationTokenRepository.delete(verificationToken);
      throw new TokenNotValidException(VERIFICATION_TOKEN_IS_EXPIRED);
    }
    ApplicationUser applicationUser = verificationToken.getUser();
    applicationUser.setEnabled(true);
    applicationUserRepository.save(applicationUser);
    verificationTokenRepository.delete(verificationToken);
  }

  public ApplicationUser findById(Long id) {
    return applicationUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));
  }

  public VerificationToken generateNewVerificationToken(String email) throws UserAlreadyActivated {
    ApplicationUser applicationUser = applicationUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
    if (applicationUser.isEnabled()) {
      throw new UserAlreadyActivated(USER_ALREADY_ACTIVATED);
    }
    if (verificationTokenRepository.findByUserId(applicationUser.getId()).isPresent()) {
      verificationTokenRepository.delete(verificationTokenRepository.findByUserId(applicationUser.getId()).get());
    }
    VerificationToken verificationToken = createVerificationTokenForUser(applicationUser, UUID.randomUUID().toString());
    verificationTokenRepository.save(verificationToken);
    return verificationToken;
  }
}