package com.ritsuki.device.service;

import com.ritsuki.device.document.DeviceData;

import java.util.concurrent.ExecutionException;

public interface DeviceDataService {

    void save(DeviceData deviceData) throws ExecutionException;

    DeviceData getLastByUuId(String uuId);
}
