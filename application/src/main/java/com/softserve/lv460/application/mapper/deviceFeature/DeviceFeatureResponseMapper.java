package com.softserve.lv460.application.mapper.deviceFeature;

import com.softserve.lv460.application.dto.deviceFeature.DeviceFeatureResponseDTO;
import com.softserve.lv460.application.entity.DeviceFeature;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.mapper.feature.FeatureMapper;
import com.softserve.lv460.application.service.FeatureService;
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
