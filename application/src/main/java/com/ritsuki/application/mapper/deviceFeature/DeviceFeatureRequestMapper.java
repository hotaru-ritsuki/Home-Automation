package com.ritsuki.application.mapper.deviceFeature;

import com.ritsuki.application.dto.deviceFeature.DeviceFeatureRequestDTO;
import com.ritsuki.application.entity.DeviceFeature;
import com.ritsuki.application.entity.id.DeviceFeatureId;
import com.ritsuki.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeviceFeatureRequestMapper implements Mapper<DeviceFeature, DeviceFeatureRequestDTO> {
  private ModelMapper modelMapper;


  @Override
  public DeviceFeature toEntity(DeviceFeatureRequestDTO dto) {
    DeviceFeature deviceFeature = modelMapper.map(dto, DeviceFeature.class);
    deviceFeature.setDeviceFeatureId(new DeviceFeatureId(dto.getDeviceId(), dto.getFeatureId()));
    return deviceFeature;
  }

  @Override
  public DeviceFeatureRequestDTO toDto(DeviceFeature entity) {
    DeviceFeatureRequestDTO dto = modelMapper.map(entity, DeviceFeatureRequestDTO.class);
    dto.setDeviceId(entity.getDeviceFeatureId().getDeviceId());
    dto.setFeatureId(entity.getDeviceFeatureId().getFeatureId());
    return dto;
  }
}
