package com.ritsuki.application.mapper.deviceTemplate;

import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateRequestDTO;
import com.ritsuki.application.entity.DeviceTemplate;
import com.ritsuki.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
