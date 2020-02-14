package com.softserve.lv460.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserChangePasswordDto {
  private String password;
  private Long id;
}
