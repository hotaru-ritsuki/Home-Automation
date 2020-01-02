package com.softserve.lv460.application.dto.feature;

import lombok.Data;
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FeatureRequest {
  private String name;

  private String description;

  private List<SupportedDeviceRequest> devicesId;
}
