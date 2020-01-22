package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplatePageRequest;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateResponse;
import com.softserve.lv460.application.service.DeviceTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/devices")
@CrossOrigin
public class DeviceTemplateController {
  private DeviceTemplateService deviceTemplateService;

  @GetMapping("/filter")
  public DataResponse<DeviceTemplateResponse> findAllByFilter(DeviceTemplatePageRequest
                                                                      deviceTemplatePageRequest) {
    return deviceTemplateService.findAllByFilter(deviceTemplatePageRequest);
  }

}
