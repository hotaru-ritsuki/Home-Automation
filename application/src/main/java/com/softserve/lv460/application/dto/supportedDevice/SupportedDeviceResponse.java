package com.softserve.lv460.application.dto.supportedDevice;

import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.entity.Feature;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SupportedDeviceResponse {
  private Long id;

  private String brand;

  private String model;

  private String type;

  private Integer releaseYear;

  private String powerSupply;

  private List<Feature> features;

  private List<Location> locations;


  public SupportedDeviceResponse(SupportedDevice supportedDevice) {
    id = supportedDevice.getId();
    brand = supportedDevice.getBrand();
    model = supportedDevice.getModel();
    type = supportedDevice.getType();
    releaseYear = supportedDevice.getReleaseYear();
    powerSupply = supportedDevice.getPowerSupply();

    features = new ArrayList<>(supportedDevice.getFeatures());
    locations = new ArrayList<>(supportedDevice.getLocations());
  }
}
