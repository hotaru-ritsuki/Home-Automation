package com.softserve.lv460.device.security.jwt;

import com.softserve.lv460.device.security.SecurityConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultJwtParser;
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
    Date expiryDate = new Date(new Date().getTime() + securityProperties.getRefreshExpirationTime());
    log.info("Access Token for home  " + homeId + " with secret: " + securityProperties.getSecret() + " created.");
    return Jwts.builder()
            .setSubject(homeId.toString())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, securityProperties.getSecret())
            .compact();

  }

  public Long getHomeIdFromJWT(String token) {
    String[] splitToken = token.split("\\.");
    String unsignedToken = splitToken[0] + "." + splitToken[1] + ".";
    DefaultJwtParser parser = new DefaultJwtParser();
    Jwt<?, ?> jwt = parser.parse(unsignedToken);
    log.info("Long " + ((Claims) jwt.getBody()).getSubject() + " from token: " + token);
    //convert to long
    return  Long.parseLong(((Claims) jwt.getBody()).getSubject());
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
