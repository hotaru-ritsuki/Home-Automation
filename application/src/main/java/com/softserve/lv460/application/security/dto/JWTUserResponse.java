package com.softserve.lv460.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTUserResponse {
  String token;
}
