package com.softserve.lv460.application.mapper.user;

import com.softserve.lv460.application.dto.user.UserInfoDTO;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.mapper.Mapper;
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