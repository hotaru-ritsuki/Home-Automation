package com.ritsuki.application.dto.rule;

import com.ritsuki.application.dto.actionRule.ActionRuleResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class RuleResponseDTO {
  private Long id;
  private String name;
  private String conditions;
  private Boolean active;
  private String description;
  private List<ActionRuleResponseDTO> actionRule;
}
