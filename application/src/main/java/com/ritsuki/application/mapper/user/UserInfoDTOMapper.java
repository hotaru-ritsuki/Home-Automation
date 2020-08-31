package com.ritsuki.application.mapper.user;

import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.mapper.Mapper;
import com.ritsuki.application.dto.user.UserInfoDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserInfoDTOMapper implements Mapper<ApplicationUser, UserInfoDTO> {
  private ModelMapper modelMapper;

  @Override
  public ApplicationUser toEntity(UserInfoDTO dto) {
    return modelMapper.map(dto, ApplicationUser.class);
  }

  @Override
  public UserInfoDTO toDto(ApplicationUser entity) {
    return modelMapper.map(entity, UserInfoDTO.class);
  }
}