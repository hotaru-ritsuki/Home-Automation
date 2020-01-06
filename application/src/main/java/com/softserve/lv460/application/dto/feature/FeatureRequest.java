package com.softserve.lv460.application.dto.feature;

import lombok.*;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateRequest;
import lombok.Data;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FeatureRequest {
  private Long id;
  private String name;
  private String description;
  private List<DeviceTemplateRequest> devicesId;
}
