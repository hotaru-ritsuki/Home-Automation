package com.softserve.lv460.application.entity;

import com.softserve.lv460.application.entity.id.DeviceTemplateFeatureId;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@AllArgsConstructor
@Entity(name = "device_features")
@IdClass(DeviceTemplateFeatureId.class)
public class DeviceFeatures {
  @Id
  @Column(name = "device_id")
  private Long deviceId;
  @Id
  @Column(name = "features_id")
  private Long featuresId;
  private String specification;
}
