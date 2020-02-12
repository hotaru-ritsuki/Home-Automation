package com.softserve.lv460.application.dto.deviceTemplate;

import com.softserve.lv460.application.dto.feature.FeatureDTO;
import lombok.Data;

import java.util.List;

@Data
public class DeviceTemplateResponseDTO {
  private Long id;
  private String brand;
  private String model;
  private String type;
  private Integer releaseYear;
  private String powerSupply;
  private String image;
  private List<FeatureDTO> features;
}
