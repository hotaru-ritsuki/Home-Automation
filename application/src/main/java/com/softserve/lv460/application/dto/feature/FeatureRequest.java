package com.softserve.lv460.application.dto.feature;

<<<<<<< HEAD
import lombok.Data;

@Data
public class FeatureRequest {
  private String name;
  private String description;
=======
import com.softserve.lv460.application.dto.supportedDevice.SupportedDeviceRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class FeatureRequest {
  private String name;

  private String description;

  private List<SupportedDeviceRequest> devicesId;
>>>>>>> origin/feature/specification
}
