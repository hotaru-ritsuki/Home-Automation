package com.softserve.lv460.application.dto.feature;

package com.softserve.lv460.application.dto.feature;

import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateRequest;
import lombok.Data;

import java.util.List;

@Data
public class FeatureRequest {
  private Long id;
  private String name;
  private String description;
}

