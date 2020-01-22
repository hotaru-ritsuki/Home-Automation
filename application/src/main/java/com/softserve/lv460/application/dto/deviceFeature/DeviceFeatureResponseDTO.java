package com.softserve.lv460.application.dto.deviceFeature;

import com.softserve.lv460.application.dto.feature.FeatureDTO;
import lombok.Data;

@Data
public class DeviceFeatureResponseDTO {
  private FeatureDTO featureDTO;
  private String specification;
}
