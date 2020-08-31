package com.ritsuki.application.mapper.localDevice;

import com.ritsuki.application.dto.localDevice.LocalDeviceResponseDTO;
import com.ritsuki.application.entity.LocalDevice;
import com.ritsuki.application.mapper.Mapper;
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
    }
}
