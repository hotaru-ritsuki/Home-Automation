package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.device.DeviceFilterRequest;
import com.softserve.lv460.application.dto.device.DeviceResponse;
import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.dto.page.PaginationRequest;
import com.softserve.lv460.application.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
  @Autowired
  private DeviceService deviceService;

  @GetMapping
  public List<DeviceResponse> findAll() {
    return deviceService.findAll();
  }

  @GetMapping("/filter")
  public DataResponse<DeviceResponse> findAllByFilter(@RequestBody DeviceFilterRequest deviceFilterRequest,
                                                      @RequestBody PaginationRequest paginationRequest) {
    return deviceService.findAllByFilter(paginationRequest, deviceFilterRequest);
  }

}
