package com.softserve.lv460.application.dto.supportedDevice;

import com.softserve.lv460.application.dto.feature.FeatureRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SupportedDeviceRequest {
  private String brand;
  private String model;
  private String type;
  private LocalDate releaseYear;
  private String powerSupply;
  private List<FeatureRequest> featuresId;
}
