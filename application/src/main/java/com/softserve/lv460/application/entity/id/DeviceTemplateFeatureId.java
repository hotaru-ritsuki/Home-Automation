package com.softserve.lv460.application.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DeviceTemplateFeatureId implements Serializable {
  private Long deviceId;
  private Long featuresId;
}
