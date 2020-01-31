package com.softserve.lv460.application.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "jwtconf")
public class SecurityConfigProperties {
  private String secret;
  private Long accessExpirationTime; // 1 day by default
  private Long refreshExpirationTime;
  private String tokenPrefix;
  private String header;
  private String signUpUrl;
  private String signInUrl;
  private String refreshTokensUrl;
  private String locationUrl;
  private String rulesUrl;
  private String telegramUrl;
  private String verifyEmail;
}