package com.softserve.lv460.device;

import com.softserve.lv460.device.action.Action;
import com.softserve.lv460.device.action.DeviceAction;
import com.softserve.lv460.device.config.PropertiesConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableConfigurationProperties(PropertiesConfig.class)
public class DeviceApplication {


  public static void main(String[] args) {
    SpringApplication.run(DeviceApplication.class, args);
  }

}
