package com.ritsuki.device.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "config")
public class PropertiesConfig {
  private String mainApplicationHostName;
  private Integer batchSize;
  private Integer batchTime;
  private Integer cacheExpiration;
  private String database;
  private String collection;
  private String telegramURL;
}
