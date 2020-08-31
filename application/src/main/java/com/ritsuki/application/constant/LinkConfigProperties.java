package com.ritsuki.application.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "env")
public class LinkConfigProperties {
  private String applicationUrl;
  private String viewUrl;
  private String deviceUrl;
}
