package com.softserve.lv460.device.service;

import com.softserve.lv460.device.dto.rule.DeviceActionDataDto;

import java.util.List;

public interface DeviceActionService {
  List<DeviceActionDataDto> findByUuId(String uuId);
}
