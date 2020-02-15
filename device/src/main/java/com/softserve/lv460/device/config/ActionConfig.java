package com.softserve.lv460.device.config;

import com.softserve.lv460.device.action.*;
import com.softserve.lv460.device.mail.EmailServiceImpl;
import com.softserve.lv460.device.repositiry.AlertListRepository;
import com.softserve.lv460.device.repositiry.DeviceActionRepository;
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

  @Bean
  ActionRegistry actionRegistry() {
    return new ActionRegistry(Arrays.asList(new DeviceAction(deviceActionRepository),
        new AlertAction(alertListRepository),
        new TelegramAction(propertiesConfig, httpClient),
        new MailAction(emailService)));
  }
}
