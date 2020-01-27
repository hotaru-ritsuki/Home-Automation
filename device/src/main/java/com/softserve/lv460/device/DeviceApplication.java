package com.softserve.lv460.device;

import com.softserve.lv460.device.config.PropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(PropertiesConfig.class)
public class DeviceApplication {


  public static void main(String[] args) {
    SpringApplication.run(DeviceApplication.class, args);
  }

}
