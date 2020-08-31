package com.ritsuki.application.dto.deviceTemplate;

import com.ritsuki.application.dto.feature.FeatureDTO;
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
