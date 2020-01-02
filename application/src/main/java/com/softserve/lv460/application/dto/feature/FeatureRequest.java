package com.softserve.lv460.application.dto.feature;

import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class FeatureRequest {
  private Long id;
  private String name;
  private String description;
  private List<SupportedDeviceRequest> devicesId;

}
