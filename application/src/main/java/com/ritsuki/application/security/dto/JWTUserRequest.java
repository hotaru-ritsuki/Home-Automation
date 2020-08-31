package com.ritsuki.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTUserRequest {
  @Email
  private String email;
  @Size(min = 8)
  private String password;
}
