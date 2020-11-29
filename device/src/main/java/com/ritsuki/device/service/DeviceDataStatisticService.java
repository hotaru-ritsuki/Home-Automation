package com.ritsuki.device.service;

import com.ritsuki.device.dto.device.DeviceDataDTO;
import com.ritsuki.device.dto.parameters.StatisticParameters;

import java.util.List;

public interface DeviceDataStatisticService {
    List<DeviceDataDTO> getStatistic(StatisticParameters statisticParameters);
}
