package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.device.DeviceFilterRequest;
import com.softserve.lv460.application.dto.device.DeviceResponse;
import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.dto.page.PaginationRequest;
import com.softserve.lv460.application.entity.Device;
import com.softserve.lv460.application.repository.DeviceRepository;
import com.softserve.lv460.application.specification.DeviceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

  @Autowired
  private DeviceRepository deviceRepository;

  public List<DeviceResponse> findAll() {
    return deviceRepository.findAll().stream()
            .map(DeviceResponse::new)
            .collect(Collectors.toList());
  }

  public DataResponse<DeviceResponse> findAllByFilter(PaginationRequest paginationRequest,
                                                      DeviceFilterRequest request) {
    DeviceSpecification deviceSpecifications = new DeviceSpecification(request);
    Page<Device> allByFilter = deviceRepository.findAll(deviceSpecifications, paginationRequest.mapToPageRequest());
    return new DataResponse<>(allByFilter.get().map(DeviceResponse::new).collect(Collectors.toList()),
            allByFilter.getTotalPages(), allByFilter.getTotalElements());
  }


}
