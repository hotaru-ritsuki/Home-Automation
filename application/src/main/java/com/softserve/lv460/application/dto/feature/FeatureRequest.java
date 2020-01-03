package com.softserve.lv460.application.dto.feature;

import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class FeatureRequest {
  private Long id;
  private String name;
  private String description;
  private List<DeviceTemplateRequest> devicesId;
}
