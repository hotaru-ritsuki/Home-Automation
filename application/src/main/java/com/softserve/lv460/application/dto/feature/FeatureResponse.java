package com.softserve.lv460.application.dto.feature;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class FeatureResponse {
  private Long id;
  private String name;
  private String description;
}
