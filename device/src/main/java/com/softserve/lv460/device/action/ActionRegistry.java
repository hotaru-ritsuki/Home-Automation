package com.softserve.lv460.device.action;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ActionRegistry {
  private DeviceAction deviceAction;


  public List<Action> getAction(String type) {
    return actionList().stream().filter((action) -> action.getType().equals(type))
            .collect(Collectors.toList());
  }


  @Bean
  List<Action> actionList() {
    List<Action> actions = new ArrayList<>();
    actions.add(deviceAction);
    return actions;
  }
}
