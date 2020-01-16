package com.softserve.lv460.application.mapper.localDevice;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequestDTO;
import com.softserve.lv460.application.dto.localDevice.LocalDeviceResponseDTO;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocalDeviceResponseMapper implements Mapper<LocalDevice, LocalDeviceResponseDTO> {
    private ModelMapper modelMapper;

    @Override
    public LocalDevice toEntity(LocalDeviceResponseDTO dto) {
        return modelMapper.map(dto, LocalDevice.class);
    }

    @Override
    public LocalDeviceResponseDTO toDto(LocalDevice entity) {
       return modelMapper.map(entity, LocalDeviceResponseDTO.class);
//        LocalDeviceResponseDTO dto = modelMapper.map(entity, LocalDeviceResponseDTO.class);
//        dto.setLocation(entity.getLocation());
//        dto.setDeviceTemplate(entity.getDeviceTemplate());
//        return dto;
    }
}
