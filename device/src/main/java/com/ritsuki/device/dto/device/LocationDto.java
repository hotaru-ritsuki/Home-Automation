package com.ritsuki.device.dto.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {
  private Long id;
  private String name;
}
