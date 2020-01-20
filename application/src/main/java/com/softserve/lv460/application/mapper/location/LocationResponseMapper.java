package com.softserve.lv460.application.mapper.location;

import com.softserve.lv460.application.dto.location.LocationResponseDTO;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocationResponseMapper implements Mapper<Location, LocationResponseDTO> {
  private ModelMapper modelMapper;

  @Override
  public Location toEntity(LocationResponseDTO dto) {
    return modelMapper.map(dto, Location.class);
  }

  @Override
  public LocationResponseDTO toDto(Location entity) {
    return modelMapper.map(entity, LocationResponseDTO.class);
  }
}
