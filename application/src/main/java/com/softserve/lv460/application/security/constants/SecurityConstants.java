package com.softserve.lv460.application.security.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwtconf")
public class SecurityConstants {
  public static String SECRET;
  public static long EXPIRATION_TIME; // 1 day
  public static String TOKEN_PREFIX;
  public static String HEADER_STRING;
  public static String SIGN_UP_URL;
}