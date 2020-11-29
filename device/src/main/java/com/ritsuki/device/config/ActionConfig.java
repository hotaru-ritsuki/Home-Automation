package com.ritsuki.device.config;

import com.ritsuki.device.action.*;
import com.ritsuki.device.config.cache.DeviceCacheConfig;
import com.ritsuki.device.repository.AlertListRepository;
import com.ritsuki.device.repository.DeviceActionRepository;
import com.ritsuki.device.service.mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class ActionConfig {

  private final PropertiesConfig propertiesConfig;
  private final CloseableHttpClient closeableHttpClient;
  private final DeviceActionRepository deviceActionRepository;
  private final AlertListRepository alertListRepository;
  private final EmailService emailService;
  private final DeviceCacheConfig deviceCacheConfig;

  @Bean
  ActionRegistry actionRegistry() {
    return new ActionRegistry(Arrays.asList(new DeviceAction(deviceActionRepository),
            new AlertAction(alertListRepository, deviceCacheConfig),
            new TelegramAction(propertiesConfig, closeableHttpClient),
            new MailAction(emailService)));
  }
}
