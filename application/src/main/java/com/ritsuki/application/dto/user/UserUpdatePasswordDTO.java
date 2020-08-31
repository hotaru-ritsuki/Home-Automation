package com.ritsuki.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatePasswordDTO {
  @NotBlank
  @Size(min = 8, max = 30)
  private String currentPassword;
  @NotBlank
  @Size(min = 8, max = 30)
  private String password;
}
