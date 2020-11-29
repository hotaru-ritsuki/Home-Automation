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

@Service("deviceActionService")
@AllArgsConstructor
public class DeviceActionServiceImpl implements DeviceActionService {

  private final DeviceActionRepository deviceActionRepository;

  @Override
  public List<DeviceActionDataDto> findByUuId(String uuId) {
    List<DeviceActionData> deviceActionDataList = deviceActionRepository.findByUuIdAndStatus(uuId, Status.WAITING);
    deviceActionDataList.forEach((data) -> data.setStatus(Status.RECEIVED));
    return deviceActionDataList.stream()
            .map(deviceActionRepository::save)
            .map(deviceActionData -> new DeviceActionDataDto(deviceActionData.getData(), deviceActionData.getTimestamp()))
            .collect(Collectors.toList());
  }

}
