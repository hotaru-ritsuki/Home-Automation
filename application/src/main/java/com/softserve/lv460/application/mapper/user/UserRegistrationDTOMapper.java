package com.softserve.lv460.application.mapper.user;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.dto.user.UserRegistrationDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class UserRegistrationDTOMapper implements Mapper<ApplicationUser, UserRegistrationDTO> {
  private final ModelMapper modelMapper;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public ApplicationUser toEntity(UserRegistrationDTO dto) {
    ApplicationUser applicationUser = modelMapper.map(dto, ApplicationUser.class);
    applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
    applicationUser.setSecret(UUID.randomUUID().toString());
    applicationUser.setEnabled(false);
    return applicationUser;
  }

  @Override
  public UserRegistrationDTO toDto(ApplicationUser entity) {
    return modelMapper.map(entity, UserRegistrationDTO.class);
  }
}
