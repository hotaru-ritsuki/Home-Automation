package com.softserve.lv460.application.security.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableConfigurationProperties
@ConfigurationProperties(prefix="jwtconf")
public class SecurityConstants {
  public String SECRET;
  public long EXPIRATION_TIME; // 1 day
  public String TOKEN_PREFIX;
  public String HEADER_STRING;
  public String SIGN_UP_URL;
}