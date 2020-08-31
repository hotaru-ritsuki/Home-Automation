package com.ritsuki.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTSuccessLogIn {
  private Long userId;
  private String accessToken;
  private String refreshToken;
  private String firstName;
}
