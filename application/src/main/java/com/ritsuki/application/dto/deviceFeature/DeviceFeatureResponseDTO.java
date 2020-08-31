package com.ritsuki.application.dto.deviceFeature;

import com.ritsuki.application.dto.feature.FeatureDTO;
import lombok.Data;

@Data
public class DeviceFeatureResponseDTO {
  private FeatureDTO featureDTO;
  private String specification;
}
