package com.softserve.lv460.application.mapper.home;

import com.softserve.lv460.application.dto.home.HomeRequestDTO;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.mapper.Mapper;
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
