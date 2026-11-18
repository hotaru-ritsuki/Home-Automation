package com.ritsuki.application.security.jwt;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.constant.SecurityConfigProperties;
import com.ritsuki.application.security.entity.UserPrincipal;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.exception.exceptions.NotFoundException;
import com.ritsuki.application.repository.ApplicationUserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
public class JWTTokenProvider {
  private final ApplicationUserRepository applicationUserRepository;
  private final SecurityConfigProperties securityProperties;

  public String generateAccessToken(Authentication authentication) {
    return this.generateAccessToken(((UserPrincipal) authentication.getPrincipal()).getUsername());
  }

  public String generateAccessToken(String email) {
    Date expiryDate = new Date(new Date().getTime() + securityProperties.getAccessExpirationTime());
    log.info("Access Token for " + email + " created.");
    return JWT.create()
            .withSubject(email)
            .withIssuedAt(new Date())
            .withExpiresAt(expiryDate)
            .sign(Algorithm.HMAC256(securityProperties.getSecret()));
  }

  public String generateRefreshToken(Authentication authentication) {
    return this.generateRefreshToken(((UserPrincipal) authentication.getPrincipal()).getUsername());
  }

  public String generateRefreshToken(String email) {
    ApplicationUser appUser = applicationUserRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_BY_EMAIL, email)));
    Date expiryDate = new Date(new Date().getTime() + securityProperties.getRefreshExpirationTime());
    log.info("Access Token for " + email + " with secret: " + appUser.getSecret() + " created.");
    return JWT.create()
            .withSubject(email)
            .withIssuedAt(new Date())
            .withExpiresAt(expiryDate)
            .sign(Algorithm.HMAC256(appUser.getSecret()));

  }

  /**
   * Reads the subject without verifying the signature (the caller verifies it afterwards
   * with the user's own secret), but still rejects expired tokens.
   */
  public String getEmailFromJWT(String token) {
    DecodedJWT jwt = JWT.decode(token);
    Instant expiresAt = jwt.getExpiresAtAsInstant();
    if (expiresAt != null && expiresAt.isBefore(Instant.now())) {
      throw new TokenExpiredException("The Token has expired on " + expiresAt + ".", expiresAt);
    }
    log.info("Email " + jwt.getSubject() + " from token: " + token);
    return jwt.getSubject();
  }

  public boolean validateAccessToken(String authAccessToken) {
    return this.isTokenValid(authAccessToken, securityProperties.getSecret());
  }

  public boolean isTokenValid(String token, String secret) {
    try {
      JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
      log.info("Given token: " + token + " with secret:" + secret + " is valid");
      return true;
    } catch (Exception e) {
      log.error("Given token is not valid: " + e.getMessage());
    }
    return false;
  }
}
