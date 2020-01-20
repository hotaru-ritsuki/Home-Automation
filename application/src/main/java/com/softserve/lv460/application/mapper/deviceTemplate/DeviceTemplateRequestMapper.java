package com.softserve.lv460.application.mapper.deviceTemplate;

import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateRequestDTO;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.service.FeatureService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DeviceTemplateRequestMapper implements Mapper<DeviceTemplate, DeviceTemplateRequestDTO> {
  private ModelMapper modelMapper;

  @Override
  public DeviceTemplate toEntity(DeviceTemplateRequestDTO dto) {
    return modelMapper.map(dto, DeviceTemplate.class);
  }

  @Override
  public DeviceTemplateRequestDTO toDto(DeviceTemplate entity) {
    return modelMapper.map(entity, DeviceTemplateRequestDTO.class);
  }
}
