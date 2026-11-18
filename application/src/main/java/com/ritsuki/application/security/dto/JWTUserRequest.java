package com.ritsuki.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTUserRequest {

  @Email
  private String email;

  @Size(min = 8)
  private String password;
}
