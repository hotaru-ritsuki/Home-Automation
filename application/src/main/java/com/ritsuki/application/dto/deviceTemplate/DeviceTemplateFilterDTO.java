package com.ritsuki.application.dto.deviceTemplate;

import lombok.Data;

import java.util.List;

@Data
public class DeviceTemplateFilterDTO {
  private String model;
  private String brand;
  private String type;
  private Integer releaseYear;
  private List<Long> featuresId;
}
