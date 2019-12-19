package com.softserve.lv460.application.service;
import com.softserve.lv460.application.entity.SupportedDevice;

import java.util.List;

public interface SupportedDeviceService {
  SupportedDevice findById(Long id);
  List<SupportedDevice> findAll();
  SupportedDevice update(SupportedDevice device);
  SupportedDevice save(SupportedDevice device);
  Long deleteById(Long id);
}
