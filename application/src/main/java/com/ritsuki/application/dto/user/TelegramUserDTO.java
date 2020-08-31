package com.ritsuki.application.dto.user;

import com.ritsuki.application.entity.TelegramUser;
import lombok.Data;

@Data
public class TelegramUserDTO {
  private Long id;
  private String email;
  private String firstName;
  private String lastName;
  private TelegramUser telegramUser;
}
