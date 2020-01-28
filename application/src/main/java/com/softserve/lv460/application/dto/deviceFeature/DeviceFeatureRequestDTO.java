package com.softserve.lv460.application.dto.deviceFeature;

import lombok.Data;

@Data
public class DeviceFeatureRequestDTO {
  private Long deviceId;
  private Long featureId;
  private String specification;
}
