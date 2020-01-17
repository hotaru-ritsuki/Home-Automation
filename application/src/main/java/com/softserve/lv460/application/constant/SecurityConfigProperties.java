package com.softserve.lv460.application.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "jwtconf")
public class SecurityConfigProperties {
  public String secret;
  public long accessExpirationTime; // 1 day by default
  public long refreshExpirationTime;
  public String tokenPrefix;
  public String header;
  public String signUpUrl;
  public String signInUrl;
}