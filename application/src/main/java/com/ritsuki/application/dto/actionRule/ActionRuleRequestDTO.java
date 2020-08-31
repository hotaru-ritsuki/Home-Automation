package com.ritsuki.application.dto.actionRule;

import lombok.Data;

@Data
public class ActionRuleRequestDTO {
  private Long ruleId;
  private Long actionId;
  private String actionSpecification;
}
