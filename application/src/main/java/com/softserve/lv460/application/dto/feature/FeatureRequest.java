package com.softserve.lv460.application.dto.feature;

import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class FeatureRequest {
  private String name;
  private String description;
  private List<DeviceTemplateRequest> devicesId;
}
