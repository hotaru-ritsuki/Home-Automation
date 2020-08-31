package com.ritsuki.device.dto.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionRuleDto {
  private String actionSpecification;
  private ActionDto action;
}
