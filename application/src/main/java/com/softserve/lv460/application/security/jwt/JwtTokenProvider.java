package com.softserve.lv460.application.security.jwt;

import com.softserve.lv460.application.constant.SecurityConfigProperties;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.exception.exceptions.NotFoundException;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultJwtParser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class JwtTokenProvider {

  private final ApplicationUserRepository applicationUserRepository;
  private final SecurityConfigProperties securityProperties;


  public String generateAccessToken(String username) {
    Date expiryDate = new Date(new Date().getTime() + securityProperties.getAccessExpirationTime());
log.info("Access Token for "+username+" created.");
    return securityProperties.getTokenPrefix() + Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, securityProperties.getSecret())
            .compact();
  }

  public String generateAccessToken(Authentication authentication) {
    return this.generateAccessToken(((UserPrincipal) authentication.getPrincipal()).getUsername());
  }

  public String generateRefreshToken(String username) {
    ApplicationUser appUser = applicationUserRepository.findByEmail(username)
            .orElseThrow(() -> new NotFoundException("Bad email"));
    Date expiryDate = new Date(new Date().getTime() + securityProperties.getRefreshExpirationTime());
    log.info("Access Token for "+username+" with secret: "+appUser.getSecret()+" created.");
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, appUser.getSecret())
            .compact();

  }

  public String generateRefreshToken(Authentication authentication) {
    return this.generateRefreshToken(((UserPrincipal)authentication.getPrincipal()).getUsername());
  }

  public String getEmailFromJWT(String token) {
    String[] splitToken = token.split("\\.");
    String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";
    DefaultJwtParser parser = new DefaultJwtParser();
    Jwt<?, ?> jwt = parser.parse(unsignedToken);
    log.info("Email "+((Claims) jwt.getBody()).getSubject()+" from token: "+token);
    return ((Claims) jwt.getBody()).getSubject();
  }

  public boolean isTokenValid(String token, String secret) {
    try {
      Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
      log.info("Given token: "+token+" with secret:"+secret+" is valid");
      return true;
    } catch (Exception e) {
      log.error("Given token is not valid: " + e.getMessage());
    }
    return false;
  }

  public boolean validateAccessToken(String authAccessToken) {
    return this.isTokenValid(authAccessToken,securityProperties.getSecret());
  }
}
