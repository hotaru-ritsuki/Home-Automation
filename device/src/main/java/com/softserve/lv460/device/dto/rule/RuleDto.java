package com.softserve.lv460.device.dto.rule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleDto {
  private Long id;
  private String name;
  private String conditions;
  private Object localDevice;
  private Object actionRule;
}
