package com.ritsuki.application.dto.deviceFeature;

import lombok.Data;

@Data
public class DeviceFeatureRequestDTO {
  private Long deviceId;
  private Long featureId;
  private String specification;
}
