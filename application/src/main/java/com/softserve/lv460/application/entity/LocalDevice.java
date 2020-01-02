package com.softserve.lv460.application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor

@Entity
@Table(name = "local_device")
public class LocalDevice {
  @Id
  private String uuid;

  @ManyToOne
  private DeviceTemplate deviceTemplate;

  @ManyToOne
  private Location location;
}
