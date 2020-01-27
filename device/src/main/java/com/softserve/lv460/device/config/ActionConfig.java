package com.softserve.lv460.device.config;

import com.softserve.lv460.device.action.ActionRegistry;
import com.softserve.lv460.device.action.DeviceAction;
import com.softserve.lv460.device.repositiry.DeviceActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class ActionConfig {
  private DeviceActionRepository deviceActionRepository;


  @Bean
  ActionRegistry actionRegistry() {
    return new ActionRegistry(Arrays.asList(new DeviceAction(deviceActionRepository)));
  }
}
