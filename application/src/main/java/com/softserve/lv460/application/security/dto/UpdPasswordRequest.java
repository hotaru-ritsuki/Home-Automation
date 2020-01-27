package com.softserve.lv460.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdPasswordRequest {
  @NotBlank
  private String currentPassword;
  @NotBlank
  private String password;
  @NotBlank
  private String confirmPassword;

}
