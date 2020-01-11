package com.softserve.lv460.device.controller;

import com.softserve.lv460.device.DeviceApplication;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.dto.deviceDto.DeviceDataDto;
import com.softserve.lv460.device.dto.parametersDto.StatisticParameters;
import com.softserve.lv460.device.service.impl.DeviceDataServiceImpl;
import com.softserve.lv460.device.service.impl.DeviceDataStatisticServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/device-data")
public class DeviceDataController {
  private DeviceDataServiceImpl deviceDataService;
  private DeviceDataStatisticServiceImpl deviceDataStatisticService;
  @PostMapping("/statistics")
  public List<DeviceDataDto> getStatistic(@Valid @RequestBody StatisticParameters statisticParameters) {
    return deviceDataStatisticService.getStatistic(statisticParameters);
  }

  @PostMapping
  public void save(@Valid @RequestBody DeviceData deviceData) throws ExecutionException {
   deviceDataService.save(deviceData);
  }


}
