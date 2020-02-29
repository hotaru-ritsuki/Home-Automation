package com.softserve.lv460.application.mapper.user;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.dto.user.TelegramUserDTO;
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

