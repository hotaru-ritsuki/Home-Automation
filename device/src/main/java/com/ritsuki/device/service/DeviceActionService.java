package com.ritsuki.device.service;

import com.ritsuki.device.dto.rule.DeviceActionDataDto;

import java.util.List;

public interface DeviceActionService {
    List<DeviceActionDataDto> findByUuId(String uuId);
}
