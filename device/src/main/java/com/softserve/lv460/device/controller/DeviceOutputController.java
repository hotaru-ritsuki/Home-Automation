package com.softserve.lv460.device.controller;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.service.impl.DeviceDataServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/device-data")
public class DeviceOutputController {
  private DeviceDataServiceImpl deviceDataService;
//  private JobLauncher jobLauncher;
//  private Job job;

  @PostMapping
  public String save (@RequestBody DeviceData deviceData){
    deviceDataService.save(deviceData);
      return "ok";
  }
}
