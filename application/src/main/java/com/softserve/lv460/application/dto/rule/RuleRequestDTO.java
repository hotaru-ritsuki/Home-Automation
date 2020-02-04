package com.softserve.lv460.application.dto.rule;

import lombok.Data;

@Data
public class RuleRequestDTO {
  private Long id;
  private String name;
  private String conditions;
  private String uuid;
  private Boolean able;
  private String description;
}
