package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.entity.SupportedDevice;
import com.softserve.lv460.application.service.SupportedDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/supported-device")
public class SupportedDeviceController {
  private SupportedDeviceService supportedDeviceService;

  @GetMapping("/get/{id}")
  public SupportedDevice getById(@PathVariable("id") Long id) {
    return supportedDeviceService.findById(id);
  }

  @GetMapping("/getAll")
  public List<SupportedDevice> getAll() {
    return supportedDeviceService.findAll();
  }

  @PostMapping("/save")
  public SupportedDevice save(@RequestBody SupportedDevice device) {
    return supportedDeviceService.save(device);
  }

  @PutMapping("/update/{id}")
  public SupportedDevice update(@PathVariable("id") Long id,@RequestBody SupportedDevice device){
      return supportedDeviceService.update(id,device);
  }

  @DeleteMapping("/delete/{id}")
  public Long deleteById(@PathVariable("id") Long id) {
    return supportedDeviceService.deleteById(id);
  }
}
