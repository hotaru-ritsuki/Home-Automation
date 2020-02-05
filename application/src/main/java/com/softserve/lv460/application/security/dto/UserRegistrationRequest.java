package com.softserve.lv460.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
  @Email
  private String email;
  @Size(min = 8, max = 30)
  private String password;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
}