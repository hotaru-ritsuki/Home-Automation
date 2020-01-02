package com.softserve.lv460.device.service;

import com.softserve.lv460.device.dto.DeviceDataDto;
import com.softserve.lv460.device.dto.StatisticParameters;

import java.util.List;

public interface DeviceDataStatisticService {
  List<DeviceDataDto> getStatistic(StatisticParameters statisticParameters);

}
