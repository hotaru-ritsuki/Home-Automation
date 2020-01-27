package com.softserve.lv460.application.mapper.localDevice;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequestDTO;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.service.LocationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocalDeviceRequestMapper implements Mapper<LocalDevice, LocalDeviceRequestDTO> {
    private ModelMapper modelMapper;
    private LocationService locationService;

    @Override
    public LocalDevice toEntity(LocalDeviceRequestDTO dto) {
        LocalDevice entity = modelMapper.map(dto, LocalDevice.class);
        entity.setLocation(locationService.findOne(dto.getLocationId()));
        return entity;
    }

    @Override
    public LocalDeviceRequestDTO toDto(LocalDevice entity) {
        LocalDeviceRequestDTO dto = modelMapper.map(entity, LocalDeviceRequestDTO.class);
        dto.setLocationId(entity.getLocation().getId());
        return dto;
    }
}
