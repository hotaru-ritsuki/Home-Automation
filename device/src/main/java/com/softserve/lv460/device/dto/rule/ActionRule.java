package com.softserve.lv460.device.dto.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionRule {
  private String actionSpecification;
  private Action action;
}
