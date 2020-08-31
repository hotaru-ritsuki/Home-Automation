package com.ritsuki.application.mapper.deviceTemplate;

import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.ritsuki.application.entity.DeviceTemplate;
import com.ritsuki.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DeviceTemplateResponseMapper implements Mapper<DeviceTemplate, DeviceTemplateResponseDTO> {
  private ModelMapper modelMapper;

  @Override
  public DeviceTemplate toEntity(DeviceTemplateResponseDTO dto) {
    return modelMapper.map(dto, DeviceTemplate.class);
  }

  @Override
  public DeviceTemplateResponseDTO toDto(DeviceTemplate entity) {
    return modelMapper.map(entity, DeviceTemplateResponseDTO.class);
  }
}
