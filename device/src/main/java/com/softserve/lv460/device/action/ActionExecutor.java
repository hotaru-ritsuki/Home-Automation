package com.softserve.lv460.device.action;

import com.softserve.lv460.device.dto.rule.ActionRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActionExecutor {
  private DeviceAction deviceAction;

  public void doAction(ActionRule action) {
    String actionType = action.getAction().getType();
    switch (actionType) {
      case "DEVICE": {
        deviceAction.execute(action);
        break;
      }
      case "TELEGRAM_BOT":
        break;
      case "LOGGER":
        break;
      case "MAIL_SENDER":
        break;
    }

  }
}
