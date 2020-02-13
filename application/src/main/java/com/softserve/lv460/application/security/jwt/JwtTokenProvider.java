package com.softserve.lv460.application.security.jwt;

import com.softserve.lv460.application.constant.SecurityConfigProperties;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.exception.exceptions.NotFoundException;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.security.entity.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultJwtParser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.softserve.lv460.application.constant.ErrorMessage.USER_NOT_FOUND_BY_EMAIL;

@Slf4j
@Component
@AllArgsConstructor
public class JwtTokenProvider {
  private final ApplicationUserRepository applicationUserRepository;
  private final SecurityConfigProperties securityProperties;

  public String generateAccessToken(Authentication authentication) {
    return this.generateAccessToken(((UserPrincipal) authentication.getPrincipal()).getUsername());
  }

  public String generateAccessToken(String email) {
    Date expiryDate = new Date(new Date().getTime() + securityProperties.getAccessExpirationTime());
    log.info("Access Token for " + email + " created.");
    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, securityProperties.getSecret())
            .compact();
  }

  public String generateRefreshToken(Authentication authentication) {
    return this.generateRefreshToken(((UserPrincipal) authentication.getPrincipal()).getUsername());
  }

  public String generateRefreshToken(String email) {
    ApplicationUser appUser = applicationUserRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
    Date expiryDate = new Date(new Date().getTime() + securityProperties.getRefreshExpirationTime());
    log.info("Refresh Token for " + email + " with secret: " + appUser.getSecret() + " created.");
    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, appUser.getSecret())
            .compact();

  }

  public String getEmailFromJWT(String token) {
    String[] splitToken = token.split("\\.");
    String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";
    DefaultJwtParser parser = new DefaultJwtParser();
    Jwt<?, ?> jwt = parser.parse(unsignedToken);
    log.info("Email " + ((Claims) jwt.getBody()).getSubject() + " from token: " + token);
    return ((Claims) jwt.getBody()).getSubject();
  }

  public boolean validateAccessToken(String authAccessToken) {
    return this.isTokenValid(authAccessToken, securityProperties.getSecret());
  }

  public boolean isTokenValid(String token, String secret) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      log.info("Given token: " + token + " with secret:" + secret + " is valid");
      return true;
    } catch (Exception e) {
      log.error("Given token is not valid: " + e.getMessage());
    }
    return false;
  }
}
