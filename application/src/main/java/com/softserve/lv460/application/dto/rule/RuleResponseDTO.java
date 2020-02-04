package com.softserve.lv460.application.dto.rule;

import com.softserve.lv460.application.dto.actionRule.ActionRuleResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class RuleResponseDTO {
  private Long id;
  private String name;
  private String conditions;
  private String uuid;
  private Boolean active;
  private String description;
  private List<ActionRuleResponseDTO> actionRule;
}
