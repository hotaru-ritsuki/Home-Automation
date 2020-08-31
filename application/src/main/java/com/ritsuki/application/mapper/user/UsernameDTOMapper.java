package com.ritsuki.application.mapper.user;

import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.mapper.Mapper;
import com.ritsuki.application.dto.user.UsernameDTO;
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