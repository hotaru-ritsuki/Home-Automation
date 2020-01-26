package com.softserve.lv460.application.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdPasswordRequest {
  @Id
  @NotBlank
  private Integer id;
  @NotBlank
  private String currentPassword;
  @NotBlank
  private String password;
}
