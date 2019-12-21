package com.softserve.lv460.application.dto.device;

import com.softserve.lv460.application.entity.Device;
import com.softserve.lv460.application.entity.Feature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeviceResponse {
  private Long id;

  private String brand;

  private String model;

  private String type;

  private LocalDate releaseYear;

  private String powerSupply;

  private List<Feature> features;

  public DeviceResponse(Device device) {
    id = device.getId();
    brand = device.getBrand();
    model = device.getModel();
    type = device.getType();
    releaseYear = device.getReleaseYear();
    powerSupply = device.getPowerSupply();

    features = new ArrayList<>(device.getFeatures());
  }
}
