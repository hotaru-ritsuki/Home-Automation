package com.softserve.lv460.application.dto.rule;

import com.softserve.lv460.application.dto.actionRule.ActionRuleResponseDTO;
import com.softserve.lv460.application.entity.LocalDevice;
import lombok.Data;

import java.util.List;

@Data
public class RuleResponseDTO {
  private Long id;
  private String name;
  private String conditions;
  private String uuid;
  private List<ActionRuleResponseDTO> actionRule;
}
