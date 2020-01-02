package com.softserve.lv460.application.dto.location;

import lombok.Data;

@Data
public class LocationRequest {
  private Long id;
  private String name;
  private Long homeId;
}

