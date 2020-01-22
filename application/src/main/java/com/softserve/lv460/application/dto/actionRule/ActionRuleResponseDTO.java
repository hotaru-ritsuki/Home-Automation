package com.softserve.lv460.application.dto.actionRule;

import com.softserve.lv460.application.dto.action.ActionDTO;
import lombok.Data;

@Data
public class ActionRuleResponseDTO {
  private ActionDTO action;
  private String actionSpecification;
}
