package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.constant.ErrorMassage;
import com.softserve.lv460.application.entity.SupportedDevice;
import com.softserve.lv460.application.exception.NotFoundException;
import com.softserve.lv460.application.repository.SupportedDeviceRepository;
import com.softserve.lv460.application.service.SupportedDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupportedDeviceServiceImpl implements SupportedDeviceService {
  private SupportedDeviceRepository supportedDeviceRepository;

  @Override
  public SupportedDevice findById(Long id) {
    return supportedDeviceRepository.findById(id).orElseThrow(() ->
            new NotFoundException(ErrorMassage.Supported_Device_Not_Found + id));
  }

  @Override
  public List<SupportedDevice> findAll() {
    return supportedDeviceRepository.findAll();
  }

  @Override
  public SupportedDevice update(SupportedDevice device) {
    SupportedDevice deviceById = findById(device.getId());
    deviceById.setBrand(deviceById.getBrand());
    deviceById.setModel(device.getModel());
    deviceById.setReleaseDate(device.getReleaseDate());
    deviceById.setType(device.getType());
    return save(deviceById);
  }

  @Override
  public SupportedDevice save(SupportedDevice device) {
    return supportedDeviceRepository.save(device);
  }

  @Override
  public Long deleteById(Long id) {
    supportedDeviceRepository.delete(findById(id));
    return id;
  }
}
