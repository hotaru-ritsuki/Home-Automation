package com.softserve.lv460.device;

import com.softserve.lv460.device.config.DeviceCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DeviceApplication.class, args);
  }

}
