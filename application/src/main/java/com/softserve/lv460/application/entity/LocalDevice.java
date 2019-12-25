package com.softserve.lv460.application.entity;

import lombok.Data;
<<<<<<< HEAD

import javax.persistence.ManyToOne;

@Data
public class LocalDevice {
    

    @ManyToOne
    private Location location;
=======
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
  private SupportedDevice supportedDevice;

  @ManyToOne
  private Location location;
>>>>>>> origin/feature/specification
}
