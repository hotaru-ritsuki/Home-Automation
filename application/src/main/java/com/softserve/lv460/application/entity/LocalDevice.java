package com.softserve.lv460.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDevice {
  @Id
  @Size(max = 32)
  private String uuid;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "location_id")
  @NotNull
  private Location locations;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "supported_device_id")
  @NotNull
  private DeviceTemplate supportedDevice;

}
