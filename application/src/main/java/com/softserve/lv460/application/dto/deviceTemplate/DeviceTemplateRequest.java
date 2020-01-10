package com.softserve.lv460.application.dto.deviceTemplate;

import com.softserve.lv460.application.dto.feature.FeatureRequest;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DeviceTemplateRequest {
  private String brand;
  private String model;
  private String type;
  private LocalDate releaseYear;
  private String powerSupply;
  private List<FeatureRequest> featuresId;
}
