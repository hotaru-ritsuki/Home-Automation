package com.softserve.lv460.application.dto.deviceTemplate;

import com.softserve.lv460.application.entity.Feature;
import com.softserve.lv460.application.entity.DeviceTemplate;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DeviceTemplateResponse {
  private Long id;
  private String brand;
  private String model;
  private String type;
  private Integer releaseYear;
  private String powerSupply;
  private List<Feature> features;

  public DeviceTemplateResponse(DeviceTemplate deviceTemplate) {
    id = deviceTemplate.getId();
    brand = deviceTemplate.getBrand();
    model = deviceTemplate.getModel();
    type = deviceTemplate.getType();
    releaseYear = deviceTemplate.getReleaseYear();
    powerSupply = deviceTemplate.getPowerSupply();
    features = new ArrayList<>(deviceTemplate.getFeatures());
  }
}
