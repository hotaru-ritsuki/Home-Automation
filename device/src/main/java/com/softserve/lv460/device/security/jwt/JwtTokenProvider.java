package com.softserve.lv460.device.security.jwt;

import com.softserve.lv460.device.config.SecurityConfigProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@AllArgsConstructor
public class JwtTokenProvider {
  private final SecurityConfigProperties securityProperties;

  public String generateAccessToken(Long homeId) {
    Date expiryDate = new Date(new Date().getTime() + securityProperties.getAccessExpirationTime());
    log.info("Access Token for home  " + homeId + " with secret: " + securityProperties.getSecret() + " created.");
    return Jwts.builder()
      .setSubject(homeId.toString())
      .setIssuedAt(new Date())
      .setExpiration(expiryDate)
      .signWith(SignatureAlgorithm.HS256, securityProperties.getSecret())
      .compact();

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
