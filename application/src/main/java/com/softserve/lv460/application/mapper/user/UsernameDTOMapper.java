package com.softserve.lv460.application.mapper.user;

import com.softserve.lv460.application.dto.user.UsernameDTO;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsernameDTOMapper implements Mapper<ApplicationUser, UsernameDTO> {
  private ModelMapper modelMapper;

  @Override
  public ApplicationUser toEntity(UsernameDTO dto) {
    return modelMapper.map(dto, ApplicationUser.class);
  }

  @Override
  public UsernameDTO toDto(ApplicationUser entity) {
    return modelMapper.map(entity, UsernameDTO.class);
  }
}