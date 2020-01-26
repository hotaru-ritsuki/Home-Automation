package com.softserve.lv460.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
  @Email
  private String email;
  @Size(min=8)
  private String password;
  private String firstName;
  private String lastName;
}