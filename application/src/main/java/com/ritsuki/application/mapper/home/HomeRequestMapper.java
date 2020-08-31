package com.ritsuki.application.mapper.home;

import com.ritsuki.application.entity.Home;
import com.ritsuki.application.dto.home.HomeRequestDTO;
import com.ritsuki.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class HomeRequestMapper implements Mapper<Home, HomeRequestDTO> {
  private ModelMapper modelMapper;

  @Override
  public Home toEntity(HomeRequestDTO dto) {
    return modelMapper.map(dto, Home.class);
  }

  @Override
  public HomeRequestDTO toDto(Home entity) {
    return modelMapper.map(entity, HomeRequestDTO.class);
  }
}
