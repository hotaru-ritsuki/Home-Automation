package com.ritsuki.device.service.impl;

import com.ritsuki.device.document.DeviceActionData;
import com.ritsuki.device.dto.enums.Status;
import com.ritsuki.device.dto.rule.DeviceActionDataDto;
import com.ritsuki.device.repository.DeviceActionRepository;
import com.ritsuki.device.service.DeviceActionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeviceActionServiceImpl implements DeviceActionService {
  private DeviceActionRepository deviceActionRepository;

  @Override
  public List<DeviceActionDataDto> findByUuId(String uuId) {
    List<DeviceActionData> deviceActionDataList = deviceActionRepository.findByUuIdAndStatus(uuId, Status.WAITING);
    deviceActionDataList.forEach((deviceActionData) -> deviceActionData.setStatus(Status.RECEIVED));
    return deviceActionRepository.saveAll(deviceActionDataList).stream().map(deviceActionData ->
        new DeviceActionDataDto(deviceActionData.getData(), deviceActionData.getTimestamp()))
        .collect(Collectors.toList());
  }

}
