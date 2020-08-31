package com.ritsuki.application.mapper.user;

import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.mapper.Mapper;
import com.ritsuki.application.dto.user.TelegramUserDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TelegramResponseMapper implements Mapper<ApplicationUser, TelegramUserDTO> {
  private ModelMapper modelMapper;
  @Override
  public ApplicationUser toEntity(TelegramUserDTO dto) {
    return modelMapper.map(dto, ApplicationUser.class);
  }

  @Override
  public TelegramUserDTO toDto(ApplicationUser entity) {
    return modelMapper.map(entity, TelegramUserDTO.class);
  }

}

