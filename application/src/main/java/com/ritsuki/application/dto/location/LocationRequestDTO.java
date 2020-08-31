package com.ritsuki.application.dto.location;

import lombok.Data;

@Data
public class LocationRequestDTO {
  private Long id;
  private String name;
  private Long homeId;
}

