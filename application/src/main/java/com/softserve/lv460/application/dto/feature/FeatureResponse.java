package com.softserve.lv460.application.dto.feature;

import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

@Getter @Setter
@NoArgsConstructor
public class FeatureResponse {
  private Long id;

  private String name;

  private String description;
}
