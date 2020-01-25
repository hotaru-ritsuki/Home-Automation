package com.softserve.lv460.application.mapper.location;

import com.softserve.lv460.application.dto.location.LocationRequestDTO;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.service.HomeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LocationRequestMapper implements Mapper<Location, LocationRequestDTO> {
  private ModelMapper modelMapper;
  private HomeService homeService;

  @Override
  public Location toEntity(LocationRequestDTO dto) {
    Location location = modelMapper.map(dto, Location.class);
    location.setHome(homeService.findOne(dto.getHomeId()));
    return location;
  }

  @Override
  public LocationRequestDTO toDto(Location entity) {
    return modelMapper.map(entity, LocationRequestDTO.class);
  }
}
