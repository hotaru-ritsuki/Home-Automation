package com.softserve.lv460.application.dto.deviceTemplate;

import lombok.Data;

import java.util.List;

@Data
public class DeviceTemplateFilterRequest {
  private String model;
  private String brand;
  private String type;
  private Integer releaseYear;
  private List<Long> featuresId;
}
