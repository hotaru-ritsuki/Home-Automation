package com.softserve.lv460.application.dto.rule;

import lombok.Data;

@Data
public class RuleRequestDTO {
  private Long id;
  private String name;
  private String conditions;
  private Boolean active;
  private String description;
}
