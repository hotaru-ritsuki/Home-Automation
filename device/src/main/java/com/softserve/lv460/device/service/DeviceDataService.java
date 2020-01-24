package com.softserve.lv460.device.service;

import com.softserve.lv460.device.document.DeviceData;

import java.util.concurrent.ExecutionException;

public interface DeviceDataService {

  void save(DeviceData deviceData) throws ExecutionException;

  DeviceData getLastByUuId(String uuId);
}
