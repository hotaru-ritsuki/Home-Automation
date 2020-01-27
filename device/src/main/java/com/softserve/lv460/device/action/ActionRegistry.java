package com.softserve.lv460.device.action;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ActionRegistry {
  private List<Action> deviceActions;


  public List<Action> getAction(String type) {
    return deviceActions.stream().filter((action) -> action.getType().equals(type))
            .collect(Collectors.toList());
  }

}
