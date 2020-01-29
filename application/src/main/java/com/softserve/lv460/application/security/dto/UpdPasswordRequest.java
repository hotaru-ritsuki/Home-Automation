package com.softserve.lv460.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdPasswordRequest {
  @NotBlank
  @Size(min=8,max=30)
  private String currentPassword;
  @NotBlank
  @Size(min = 8,max=30)
  private String password;
}
