package com.ritsuki.device.config;

import com.ritsuki.device.action.*;
import com.ritsuki.device.config.cache.DeviceCacheConfig;
import com.ritsuki.device.mail.EmailServiceImpl;
import com.ritsuki.device.repository.AlertListRepository;
import com.ritsuki.device.repository.DeviceActionRepository;
import lombok.AllArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class ActionConfig {
  private PropertiesConfig propertiesConfig;
  private CloseableHttpClient httpClient;
  private DeviceActionRepository deviceActionRepository;
  private AlertListRepository alertListRepository;
  private EmailServiceImpl emailService;
  private DeviceCacheConfig deviceCacheConfig;

  @Bean
  ActionRegistry actionRegistry() {
    return new ActionRegistry(Arrays.asList(new DeviceAction(deviceActionRepository),
            new AlertAction(alertListRepository, deviceCacheConfig),
            new TelegramAction(propertiesConfig, httpClient),
            new MailAction(emailService)));
  }
}
