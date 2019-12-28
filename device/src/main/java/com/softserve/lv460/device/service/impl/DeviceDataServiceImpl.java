package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.repositiry.DeviceDataRepository;
import com.softserve.lv460.device.service.DeviceDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeviceDataServiceImpl implements DeviceDataService {
  private DeviceDataRepository repository;


  @Override
  public void save(DeviceData deviceData) {
    System.out.println(repository.save(deviceData));
  }
}
