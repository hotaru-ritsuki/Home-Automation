package com.ritsuki.application.mapper.localDevice;

import com.ritsuki.application.dto.localDevice.LocalDeviceRespDTO;
import com.ritsuki.application.entity.LocalDevice;
import com.ritsuki.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocalDeviceRespMapper implements Mapper<LocalDevice, LocalDeviceRespDTO> {
  private ModelMapper modelMapper;
  //private LocationService locationService;

  @Override
  public LocalDevice toEntity(LocalDeviceRespDTO dto) {
    return modelMapper.map(dto, LocalDevice.class);
  }

  @Override
  public LocalDeviceRespDTO toDto(LocalDevice entity) {
    LocalDeviceRespDTO dto = modelMapper.map(entity, LocalDeviceRespDTO.class);
    dto.setHomeId(entity.getLocation().getHome().getId());
    return dto;

//    LocalDeviceRespDTO dto = modelMapper.map(entity, LocalDeviceRespDTO.class);
//    return dto;
  }
}
