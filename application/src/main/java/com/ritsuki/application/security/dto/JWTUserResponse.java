package com.ritsuki.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class JWTUserResponse {
  @NotNull
  private String accessToken;
  @NotNull
  private String refreshToken;
}
