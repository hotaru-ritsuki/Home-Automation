package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateFilterDTO;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.softserve.lv460.application.dto.data.DataResponse;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.exception.exceptions.NotFoundException;
import com.softserve.lv460.application.exception.exceptions.NotUpdatedException;
import com.softserve.lv460.application.mapper.deviceTemplate.DeviceTemplateResponseMapper;
import com.softserve.lv460.application.repository.DeviceTemplateRepository;
import com.softserve.lv460.application.specification.DeviceTemplateSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DeviceTemplateService {
  private DeviceTemplateRepository deviceTemplateRepository;
  private DeviceTemplateResponseMapper responseMapper;

  public DeviceTemplate findById(Long id) {
    return deviceTemplateRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.DEVICE_TEMPLATE_NOT_FOUND_BY_ID + id));
  }

  public List<String> findAllBrands() {
    return deviceTemplateRepository.findAllBrands();
  }

  public List<Integer> findAllReleaseYears() {
    return deviceTemplateRepository.findAllReleaseYears();
  }

  public DeviceTemplate save(DeviceTemplate deviceTemplate) {
    return deviceTemplateRepository.save(deviceTemplate);
  }

  public Long delete(Long id) {
    if (!deviceTemplateRepository.findById(id).isPresent()) {
      throw new NotDeletedException(ErrorMessage.DEVICE_TEMPLATE_NOT_DELETED_BY_ID + id);
    }
    deviceTemplateRepository.deleteById(id);
    return id;
  }

  public DeviceTemplate update(DeviceTemplate deviceTemplate) {
    if (!deviceTemplateRepository.findById(deviceTemplate.getId()).isPresent()) {
      throw new NotUpdatedException(ErrorMessage.DEVICE_TEMPLATE_NOT_UPDATED_BY_ID);
    }
    return deviceTemplateRepository.save(deviceTemplate);
  }

  public DataResponse<DeviceTemplateResponseDTO> findAllByFilter(Integer page,
                                                                 DeviceTemplateFilterDTO request) {
    DeviceTemplateSpecification deviceTemplateSpecifications = new DeviceTemplateSpecification(request);
    Page<DeviceTemplate> allByFilter = deviceTemplateRepository.findAll(deviceTemplateSpecifications,
            PageRequest.of(page, 20));
    return new DataResponse<>(allByFilter.get().map(e -> responseMapper.toDto(e))
            .collect(Collectors.toList()), allByFilter.getTotalPages(), allByFilter.getTotalElements());
  }
}
