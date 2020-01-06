package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.dto.DeviceDataDto;
import com.softserve.lv460.device.dto.StatisticParameters;
import com.softserve.lv460.device.repositiry.DeviceDataStatisticRepository;
import com.softserve.lv460.device.service.DeviceDataStatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeviceDataStatisticServiceImpl implements DeviceDataStatisticService {
  private DeviceDataStatisticRepository deviceDataStatisticRepository;

  @Override
  public List<DeviceDataDto> getStatistic(StatisticParameters statisticParameters) {
    return deviceDataStatisticRepository.getStatistic(statisticParameters.getType(), statisticParameters.getFrom(),
            statisticParameters.getTo()).stream()
            .map(deviceData -> new DeviceDataDto(deviceData.getData(), deviceData.getTimestamp()))
            .collect(Collectors.toList());
  }
}
