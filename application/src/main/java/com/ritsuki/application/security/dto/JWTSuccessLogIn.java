package com.ritsuki.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTSuccessLogIn {
  @NotNull
  private Long userId;
  @NotNull
  private String accessToken;
  @NotNull
  private String refreshToken;
  @NotNull
  private String firstName;
}
