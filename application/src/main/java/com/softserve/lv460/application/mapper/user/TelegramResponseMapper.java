package com.softserve.lv460.application.mapper.user;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.TelegramUserResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class TelegramResponseMapper implements Mapper<ApplicationUser, TelegramUserResponse> {
  private ModelMapper modelMapper;
  @Override
  public ApplicationUser toEntity(TelegramUserResponse dto) {
    return modelMapper.map(dto, ApplicationUser.class);
  }

  @Override
  public TelegramUserResponse toDto(ApplicationUser entity) {
    return modelMapper.map(entity, TelegramUserResponse.class);
  }

}

