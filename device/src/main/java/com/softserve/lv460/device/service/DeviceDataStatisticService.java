package com.softserve.lv460.device.service;

import com.softserve.lv460.device.dto.device.DeviceDataDto;
import com.softserve.lv460.device.dto.parameters.StatisticParameters;

import java.util.List;

public interface DeviceDataStatisticService {
  List<DeviceDataDto> getStatistic(StatisticParameters statisticParameters);

}
