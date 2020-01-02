package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceFilterAndPaginationRequest;
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceResponse;
import com.softserve.lv460.application.service.SupportedDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/devices")
@CrossOrigin
public class SupportedDeviceController {
  private SupportedDeviceService supportedDeviceService;

  @GetMapping("/filter")
  public DataResponse<SupportedDeviceResponse> findAllByFilter(SupportedDeviceFilterAndPaginationRequest
                                                                       supportedDeviceFilterAndPaginationRequest) {
    return supportedDeviceService.findAllByFilter(supportedDeviceFilterAndPaginationRequest);
  }

}
