package com.softserve.lv460.device.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class DeviceCache {

  @Cacheable(value = "deviceCache",key = "#uu")
  public Boolean get(String uu){
    return
  }
}
