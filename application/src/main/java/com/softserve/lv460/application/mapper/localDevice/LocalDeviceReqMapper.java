package com.softserve.lv460.application.mapper.localDevice;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceReqDTO;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.service.DeviceTemplateService;
import com.softserve.lv460.application.service.LocationService;
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
