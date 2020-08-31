package com.ritsuki.application.mapper.deviceFeature;

import com.ritsuki.application.dto.deviceFeature.DeviceFeatureResponseDTO;
import com.ritsuki.application.entity.DeviceFeature;
import com.ritsuki.application.mapper.Mapper;
import com.ritsuki.application.mapper.feature.FeatureMapper;
import com.ritsuki.application.service.FeatureService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DeviceFeatureResponseMapper implements Mapper<DeviceFeature, DeviceFeatureResponseDTO> {
  private ModelMapper modelMapper;
  private FeatureService featureService;
  private FeatureMapper featureMapper;

  @Override
  public DeviceFeature toEntity(DeviceFeatureResponseDTO dto) {
    return modelMapper.map(dto, DeviceFeature.class);
  }

  @Override
  public DeviceFeatureResponseDTO toDto(DeviceFeature entity) {
    DeviceFeatureResponseDTO dto = modelMapper.map(entity, DeviceFeatureResponseDTO.class);
    dto.setFeatureDTO(featureMapper.toDto(featureService.findFeature(entity.getDeviceFeatureId().getFeatureId())));
    return dto;
  }
}
