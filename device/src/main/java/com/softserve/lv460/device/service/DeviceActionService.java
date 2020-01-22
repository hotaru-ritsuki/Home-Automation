package com.softserve.lv460.device.service;

import com.softserve.lv460.device.document.DeviceActionData;

public interface DeviceActionService {
  DeviceActionData findByUuId(String uuId);
}
