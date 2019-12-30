package com.softserve.lv460.device.controller;

import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.dto.DeviceDataDto;
import com.softserve.lv460.device.service.impl.DeviceDataServiceImpl;
import com.softserve.lv460.device.service.impl.DeviceDataStatisticServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/deviceData")
public class DeviceDataController {
  private DeviceDataServiceImpl deviceDataService;
  private DeviceDataStatisticServiceImpl deviceDataStatisticService;

  @GetMapping("/statistic")
  public List<DeviceDataDto> getStatistic(@RequestParam("type") String type,
                                          @RequestParam("from") String from,
                                          @RequestParam("to") String to

  ) {
    return deviceDataStatisticService.getStatistic(type, from, to);
  }

  @PostMapping
  public void save(@Valid @RequestBody DeviceData deviceData) throws ExecutionException {
    deviceDataService.save(deviceData);
  }
}
