package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplatePageRequest;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateResponse;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.repository.DeviceTemplateRepository;
import com.softserve.lv460.application.specification.DeviceTemplateSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DeviceTemplateService {
  private DeviceTemplateRepository deviceTemplateRepository;

  public DataResponse<DeviceTemplateResponse> findAllByFilter(DeviceTemplatePageRequest
                                                                      deviceTemplatePageRequest) {
    if (deviceTemplatePageRequest.getSupportedDeviceFilterRequest() == null) {
      Page<DeviceTemplate> allDevices =
              deviceTemplateRepository.findAll(deviceTemplatePageRequest.getPaginationRequest().mapToPageRequest());
      return new DataResponse<>(allDevices.get().map(DeviceTemplateResponse::new).collect(Collectors.toList()),
              allDevices.getTotalPages(), allDevices.getTotalElements());
    }
    DeviceTemplateSpecification deviceTemplateSpecifications =
            new DeviceTemplateSpecification(deviceTemplatePageRequest.getSupportedDeviceFilterRequest());
    Page<DeviceTemplate> allByFilter = deviceTemplateRepository.findAll(deviceTemplateSpecifications,
            deviceTemplatePageRequest.getPaginationRequest().mapToPageRequest());
    return new DataResponse<>(allByFilter.get().map(DeviceTemplateResponse::new).collect(Collectors.toList()),
            allByFilter.getTotalPages(), allByFilter.getTotalElements());

  }


}
