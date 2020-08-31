package com.ritsuki.application.mapper.localDevice;

import com.ritsuki.application.dto.localDevice.LocalDeviceReqDTO;
import com.ritsuki.application.entity.LocalDevice;
import com.ritsuki.application.mapper.Mapper;
import com.ritsuki.application.service.DeviceTemplateService;
import com.ritsuki.application.service.LocationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocalDeviceReqMapper implements Mapper<LocalDevice, LocalDeviceReqDTO> {
  private ModelMapper modelMapper;
  private LocationService locationService;
  private DeviceTemplateService deviceTemplateService;

  @Override
  public LocalDevice toEntity(LocalDeviceReqDTO dto) {
    LocalDevice entity = modelMapper.map(dto, LocalDevice.class);
    entity.setLocation(locationService.findOne(dto.getLocationId()));
    entity.setDeviceTemplate(deviceTemplateService.findById(dto.getDeviceTemplateId()));
    return entity;
  }

  @Override
  public LocalDeviceReqDTO toDto(LocalDevice entity) {
    LocalDeviceReqDTO dto = modelMapper.map(entity, LocalDeviceReqDTO.class);
    dto.setLocationId(entity.getLocation().getId());
    return dto;
  }
}
