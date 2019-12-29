package com.softserve.lv460.device.controller;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.service.impl.DeviceDataServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/deviceData")
public class DeviceOutputController {
  private DeviceDataServiceImpl deviceDataService;

  @PostMapping
  public void save (@Valid @RequestBody DeviceData deviceData) throws ExecutionException {
    deviceDataService.save(deviceData);
  }

  @GetMapping("/getStatisticByTemperature")
  public String getStatisticByTemperature (){
      return "";
  }


}
