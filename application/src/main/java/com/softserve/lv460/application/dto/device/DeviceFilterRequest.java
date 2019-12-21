package com.softserve.lv460.application.dto.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DeviceFilterRequest {
  private String model;

  private String brand;

  private String type;

  private LocalDate releaseYear;
}
