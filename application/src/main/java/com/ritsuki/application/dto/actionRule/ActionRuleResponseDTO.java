package com.ritsuki.application.dto.actionRule;

import com.ritsuki.application.dto.action.ActionDTO;
import lombok.Data;

@Data
public class ActionRuleResponseDTO {
  private ActionDTO action;
  private String actionSpecification;
}
