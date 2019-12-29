package com.softserve.lv460.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LocalDevice {
  @Id
  private String uuid;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "location_id")
  private Location locations;
  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  private SupportedDevice supportedDevice;
}
