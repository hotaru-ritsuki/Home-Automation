package com.ritsuki.application.dto.deviceTemplate;

import lombok.Data;

import java.util.List;

@Data
public class DeviceTemplateRequestDTO {
  private Long id;
  private String brand;
  private String model;
  private String type;
  private Integer releaseYear;
  private String powerSupply;
  private String image;
}
