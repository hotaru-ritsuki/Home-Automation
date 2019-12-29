package com.softserve.lv460.application.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
public class LocalDevice {
  @Id
  private String uuid;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "location_id")
  private Location locations;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "device_id")
  private SupportedDevice supportedDevice;
}
