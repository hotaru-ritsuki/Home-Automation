package com.softserve.lv460.device.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "device")
public class SecurityConfigProperties {
  private String secret;
  private String tokenPrefix;
  private String header;
  private Long accessExpirationTime;
}