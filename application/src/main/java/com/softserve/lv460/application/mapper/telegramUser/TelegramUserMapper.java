package com.softserve.lv460.application.mapper.telegramUser;

import com.softserve.lv460.application.dto.telegramUser.TelegramUserDTO;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TelegramUserMapper implements Mapper<TelegramUser, TelegramUserDTO> {
  private ModelMapper modelMapper;

  @Override
  public TelegramUser toEntity(TelegramUserDTO dto) {
    return modelMapper.map(dto, TelegramUser.class);
  }

  @Override
  public TelegramUserDTO toDto(TelegramUser entity) {
    return modelMapper.map(entity, TelegramUserDTO.class);
  }
}
