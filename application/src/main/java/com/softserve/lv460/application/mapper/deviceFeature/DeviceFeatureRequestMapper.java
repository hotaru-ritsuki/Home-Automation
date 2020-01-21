package com.softserve.lv460.application.mapper.deviceFeature;

import com.softserve.lv460.application.dto.deviceFeature.DeviceFeatureRequestDTO;
import com.softserve.lv460.application.entity.DeviceFeature;
import com.softserve.lv460.application.entity.id.DeviceFeatureId;
import com.softserve.lv460.application.mapper.Mapper;
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
