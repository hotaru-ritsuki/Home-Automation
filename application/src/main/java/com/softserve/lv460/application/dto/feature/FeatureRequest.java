package com.softserve.lv460.application.dto.feature;

import com.softserve.lv460.application.dto.device.DeviceRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class FeatureRequest {
  private String name;

  private String description;

  private List<DeviceRequest> devicesId;
}
