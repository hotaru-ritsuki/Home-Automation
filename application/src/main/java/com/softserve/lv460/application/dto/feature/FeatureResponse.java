package com.softserve.lv460.application.dto.feature;

<<<<<<< HEAD
import lombok.Data;

@Data
public class FeatureResponse {
  private Long id;
  private String name;
=======
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class FeatureResponse {
  private Long id;

  private String name;

>>>>>>> origin/feature/specification
  private String description;
}
