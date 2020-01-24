package com.softserve.lv460.device.action;

import com.softserve.lv460.device.dto.rule.ActionRule;

public interface Action {
  void execute(ActionRule actionRule);
  String getType();
}
