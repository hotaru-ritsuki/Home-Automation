package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.dto.page.PaginationRequest;
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceFilterRequest;
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceResponse;
import com.softserve.lv460.application.service.SupportedDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/device")
public class SupportedDeviceController {
  private final SupportedDeviceService supportedDeviceService;

  public SupportedDeviceController(SupportedDeviceService supportedDeviceService) {
    this.supportedDeviceService = supportedDeviceService;
  }

  @GetMapping
  public List<SupportedDeviceResponse> findAll() {
    return supportedDeviceService.findAll();
  }

  @GetMapping("/filter")
  public DataResponse<SupportedDeviceResponse> findAllByFilter(SupportedDeviceFilterRequest supportedDeviceFilterRequest,
                                                               PaginationRequest paginationRequest) {
    return supportedDeviceService.findAllByFilter(paginationRequest, supportedDeviceFilterRequest);
  }

}
