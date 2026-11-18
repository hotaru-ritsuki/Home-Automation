package com.ritsuki.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
  @Email
  private String email;
  @Size(min = 8, max = 30)
  private String password;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
}