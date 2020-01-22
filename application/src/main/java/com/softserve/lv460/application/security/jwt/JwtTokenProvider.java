package com.softserve.lv460.application.security.jwt;

import com.softserve.lv460.application.constant.SecurityConfigProperties;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

  private final ApplicationUserRepository applicationUserRepository;
  private final SecurityConfigProperties securityProperties;


  public String generateAccessToken(Authentication authentication) {

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

    Date expiryDate = new Date(new Date().getTime() + securityProperties.getAccessExpirationTime());

    return securityProperties.getTokenPrefix() + Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, securityProperties.getSecret())
            .compact();
  }

  public String generateRefreshToken(Authentication authentication) {

    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    ApplicationUser applicationUser = applicationUserRepository.findByEmail(userPrincipal.getUsername()).get();
            Date expiryDate = new Date(new Date().getTime() + securityProperties.getRefreshExpirationTime());

    return securityProperties.getTokenPrefix() + Jwts.builder()
            .setSubject(userPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, applicationUser.getSecret())
            .compact();
  }

  public String getEmailFromJWT(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(securityProperties.getSecret())
            .parseClaimsJws(token)
            .getBody();

    return claims.getSubject();
  }

  public boolean validateAccessToken(String authAccessToken) {
    try {
      Jwts.parser().setSigningKey(securityProperties.getSecret()).parseClaimsJws(authAccessToken);
      return true;
    } catch (RuntimeException e){
      logger.error(e.getMessage());
    }
    return false;
  }

}
