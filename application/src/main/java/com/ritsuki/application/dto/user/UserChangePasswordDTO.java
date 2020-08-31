package com.ritsuki.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserChangePasswordDTO {
  private String password;
  private Long id;
}
