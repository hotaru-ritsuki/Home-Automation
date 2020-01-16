package com.softserve.lv460.device.controller;



import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.dto.deviceDto.DeviceDataDto;
import com.softserve.lv460.device.dto.parametersDto.StatisticParameters;
import com.softserve.lv460.device.service.impl.DeviceDataServiceImpl;
import com.softserve.lv460.device.service.impl.DeviceDataStatisticServiceImpl;
import lombok.AllArgsConstructor;
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

//  @GetMapping("/current-sensor-indication")
//  public String getCurrentSensorIndication (){
//      return "";
//  }

  @PostMapping("/statistics")
  public List<DeviceDataDto> getStatistic(@Valid @RequestBody StatisticParameters statisticParameters) {
    return deviceDataStatisticService.getStatistic(statisticParameters);
  }

  @PostMapping
  public void save(@Valid @RequestBody DeviceData deviceData) throws ExecutionException {
   deviceDataService.save(deviceData);
  }


}
