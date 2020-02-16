package com.softserve.lv460.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@ConfigurationPropertiesScan("com.softserve.lv460.device.config")
public class DeviceApplication {


  public static void main(String[] args) {
    SpringApplication.run(DeviceApplication.class, args);
  }

  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

}
