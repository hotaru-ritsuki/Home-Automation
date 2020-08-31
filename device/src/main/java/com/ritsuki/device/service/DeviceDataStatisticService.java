package com.ritsuki.device.service;

import com.ritsuki.device.dto.device.DeviceDataDto;
import com.ritsuki.device.dto.parameters.StatisticParameters;

import java.util.List;

public interface DeviceDataStatisticService {
  List<DeviceDataDto> getStatistic(StatisticParameters statisticParameters);

}
