package com.softserve.lv460.application.mapper.user;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.security.dto.UserRegistrationRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserRegistrationRequestMapper implements Mapper<ApplicationUser, UserRegistrationRequest> {
  private final ModelMapper modelMapper;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public ApplicationUser toEntity(UserRegistrationRequest dto) {
    ApplicationUser applicationUser = modelMapper.map(dto, ApplicationUser.class);
    applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
    applicationUser.setSecret(UUID.randomUUID().toString());
    applicationUser.setEnabled(false);
    return applicationUser;
  }

  @Override
  public UserRegistrationRequest toDto(ApplicationUser entity) {
    return modelMapper.map(entity, UserRegistrationRequest.class);
  }
}
