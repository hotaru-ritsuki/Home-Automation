package com.softserve.lv460.application.mapper.user;

import com.softserve.lv460.application.Application;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JWTUserRequestMapper implements Mapper<ApplicationUser, JWTUserRequest> {
  private ModelMapper modelMapper;

  @Override
  public ApplicationUser toEntity(JWTUserRequest dto) {
    return modelMapper.map(dto, ApplicationUser.class);
  }

  @Override
  public JWTUserRequest toDto(ApplicationUser entity) {
    return modelMapper.map(entity, JWTUserRequest.class);
  }
}
