package com.ritsuki.application.dto.home;

import lombok.Data;

@Data
public class HomeRequestDTO {
  private Long id;
  private String country;
  private String city;
  private String addressa;
  private String name;
}
