package com.softserve.lv460.device.service;

import com.softserve.lv460.device.dto.deviceDto.DeviceDataDto;
import com.softserve.lv460.device.dto.parametersDto.StatisticParameters;

import java.util.List;

public interface DeviceDataStatisticService {
  List<DeviceDataDto> getStatistic(StatisticParameters statisticParameters);

}
