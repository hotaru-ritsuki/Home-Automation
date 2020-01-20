package com.softserve.lv460.application.mapper.home;

import com.softserve.lv460.application.dto.home.HomeResponseDTO;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.mapper.location.LocationResponseMapper;
import com.softserve.lv460.application.service.LocationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class HomeResponseMapper implements Mapper<Home, HomeResponseDTO> {
  private ModelMapper modelMapper;
  private LocationService locationService;
  private LocationResponseMapper responseMapper;

  @Override
  public Home toEntity(HomeResponseDTO dto) {
    return modelMapper.map(dto, Home.class);
  }

  @Override
  public HomeResponseDTO toDto(Home entity) {
    HomeResponseDTO dto = modelMapper.map(entity, HomeResponseDTO.class);
    dto.setLocations(locationService.findByHome(entity.getId()).stream().map(responseMapper::toDto).collect(Collectors.toList()));
    return dto;
  }
}
