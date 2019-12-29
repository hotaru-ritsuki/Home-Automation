package com.softserve.lv460.application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor

@Entity
@Table(name = "local_device")
public class LocalDevice {
  @Id
  @Size(max=32)
  private String uuid;

  @ManyToOne
  private SupportedDevice supportedDevice;

  @ManyToOne
  private Location location;
}
