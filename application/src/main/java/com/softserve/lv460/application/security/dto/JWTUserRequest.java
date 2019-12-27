package com.softserve.lv460.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTUserRequest {
  String email;
  String password;
}
