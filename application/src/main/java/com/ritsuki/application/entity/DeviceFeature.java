package com.ritsuki.application.entity;

import com.ritsuki.application.entity.id.DeviceFeatureId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "device_features")
public class DeviceFeature {

  @EmbeddedId
  private DeviceFeatureId deviceFeatureId;

  @NotNull
  private String specification;
}

