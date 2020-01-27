package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.document.DeviceActionData;
import com.softserve.lv460.device.dto.enums.Status;
import com.softserve.lv460.device.dto.rule.DeviceActionDataDto;
import com.softserve.lv460.device.repositiry.DeviceActionRepository;
import com.softserve.lv460.device.service.DeviceActionService;
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
