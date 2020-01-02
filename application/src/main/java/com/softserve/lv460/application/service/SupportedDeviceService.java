package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceFilterAndPaginationRequest;
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceResponse;
import com.softserve.lv460.application.entity.SupportedDevice;
import com.softserve.lv460.application.repository.SupportedDeviceRepository;
import com.softserve.lv460.application.specification.SupportedDeviceSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SupportedDeviceService {
  private SupportedDeviceRepository supportedDeviceRepository;

  public DataResponse<SupportedDeviceResponse> findAllByFilter(SupportedDeviceFilterAndPaginationRequest
                                                                       supportedDeviceFilterAndPaginationRequest) {
    if (supportedDeviceFilterAndPaginationRequest.getSupportedDeviceFilterRequest() == null) {
      Page<SupportedDevice> allDevices =
              supportedDeviceRepository.findAll(supportedDeviceFilterAndPaginationRequest.getPaginationRequest().mapToPageRequest());
      return new DataResponse<>(allDevices.get().map(SupportedDeviceResponse::new).collect(Collectors.toList()),
              allDevices.getTotalPages(), allDevices.getTotalElements());
    }
    SupportedDeviceSpecification supportedDeviceSpecifications =
            new SupportedDeviceSpecification(supportedDeviceFilterAndPaginationRequest.getSupportedDeviceFilterRequest());
    Page<SupportedDevice> allByFilter = supportedDeviceRepository.findAll(supportedDeviceSpecifications,
            supportedDeviceFilterAndPaginationRequest.getPaginationRequest().mapToPageRequest());
    return new DataResponse<>(allByFilter.get().map(SupportedDeviceResponse::new).collect(Collectors.toList()),
            allByFilter.getTotalPages(), allByFilter.getTotalElements());

  }


}
