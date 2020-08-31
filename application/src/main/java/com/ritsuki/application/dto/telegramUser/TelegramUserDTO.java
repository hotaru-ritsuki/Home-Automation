package com.ritsuki.application.dto.telegramUser;

import lombok.Data;

@Data
public class TelegramUserDTO {
  private Long id;
  private String username;
  private String chatId;
}
