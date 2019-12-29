package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceFilterRequest;
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceResponse;
import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.dto.page.PaginationRequest;
import com.softserve.lv460.application.entity.SupportedDevice;
import com.softserve.lv460.application.repository.SupportedDeviceRepository;
import com.softserve.lv460.application.specification.SupportedDeviceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupportedDeviceService {

  @Autowired
  private SupportedDeviceRepository supportedDeviceRepository;

  public List<SupportedDeviceResponse> findAll() {
    return supportedDeviceRepository.findAll().stream()
            .map(SupportedDeviceResponse::new)
            .collect(Collectors.toList());
  }

  public DataResponse<SupportedDeviceResponse> findAllByFilter(PaginationRequest paginationRequest,
                                                               SupportedDeviceFilterRequest request) {
    SupportedDeviceSpecification supportedDeviceSpecifications = new SupportedDeviceSpecification(request);
    Page<SupportedDevice> allByFilter = supportedDeviceRepository.findAll(supportedDeviceSpecifications, paginationRequest.mapToPageRequest());
    return new DataResponse<>(allByFilter.get().map(SupportedDeviceResponse::new).collect(Collectors.toList()),
            allByFilter.getTotalPages(), allByFilter.getTotalElements());

  }
}
