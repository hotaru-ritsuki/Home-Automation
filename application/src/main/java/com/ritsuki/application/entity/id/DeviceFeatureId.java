package com.ritsuki.application.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DeviceFeatureId implements Serializable {
  @Column(name = "device_id")
  private Long deviceId;
  @Column(name = "features_id")
  private Long featureId;
}
