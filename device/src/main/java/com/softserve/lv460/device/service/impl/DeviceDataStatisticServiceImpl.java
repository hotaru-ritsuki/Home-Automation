package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.dto.DeviceDataDto;
import com.softserve.lv460.device.repositiry.DeviceDataStatisticRepository;
import com.softserve.lv460.device.service.DeviceDataStatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeviceDataStatisticServiceImpl implements DeviceDataStatisticService {
  private DeviceDataStatisticRepository deviceDataStatisticRepository;

  public List<DeviceDataDto> getStatistic(String type, LocalDateTime from, LocalDateTime to) {
    return deviceDataStatisticRepository.getStatistic(type, from, to).stream()
            .map(deviceData -> new DeviceDataDto(deviceData.getData(), deviceData.getTimestamp()))
            .collect(Collectors.toList());
  }


}
