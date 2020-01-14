package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplatePageRequest;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateRequestDTO;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.softserve.lv460.application.dto.page.DataResponse;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.mapper.deviceTemplate.DeviceTemplateRequestMapper;
import com.softserve.lv460.application.mapper.deviceTemplate.DeviceTemplateResponseMapper;
import com.softserve.lv460.application.service.DeviceTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/devices")
@CrossOrigin
public class DeviceTemplateController {
  private DeviceTemplateService deviceTemplateService;
  private DeviceTemplateRequestMapper requestMapper;
  private DeviceTemplateResponseMapper responseMapper;

  @GetMapping("/brands")
  public List<String> findAllBrands() {
    return deviceTemplateService.findAllBrands();
  }

  @GetMapping("/years")
  public List<Integer> findAllReleaseYears() {
    return deviceTemplateService.findAllReleaseYears();
  }

  @GetMapping
  public List<DeviceTemplateResponseDTO> findAll() {
    List<DeviceTemplate> allDevices = deviceTemplateService.findAll();
    return allDevices.stream().map(responseMapper::toDto).collect(Collectors.toList());
  }

  @PutMapping
  public DeviceTemplateRequestDTO update(@RequestBody DeviceTemplateRequestDTO dto) {
    DeviceTemplate deviceTemplate = requestMapper.toEntity(dto);
    deviceTemplateService.update(deviceTemplate);
    return dto;
  }

  @PostMapping
  public DeviceTemplateRequestDTO save(@RequestBody DeviceTemplateRequestDTO dto) {
    DeviceTemplate deviceTemplate = requestMapper.toEntity(dto);
    deviceTemplateService.save(deviceTemplate);
    return dto;
  }

  @DeleteMapping
  public Long delete(@RequestParam Long id) {
    deviceTemplateService.delete(id);
    return id;
  }

  @GetMapping("/filter")
  public DataResponse<DeviceTemplateResponseDTO> findAllByFilter(DeviceTemplatePageRequest
                                                                         deviceTemplatePageRequest) {
    return deviceTemplateService.findAllByFilter(deviceTemplatePageRequest);
  }

}
