package com.ritsuki.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTUserResponse {
  private String accessToken;
  private String refreshToken;
}
