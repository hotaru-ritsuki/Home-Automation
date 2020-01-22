package com.softserve.lv460.device.action;

import com.softserve.lv460.device.controller.DeviceDataController;
import com.softserve.lv460.device.document.DeviceActionData;
import com.softserve.lv460.device.dto.enums.Status;
import com.softserve.lv460.device.dto.rule.ActionRule;
import com.softserve.lv460.device.repositiry.DeviceActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeviceAction implements Action {
  private final DeviceActionRepository deviceActionRepository;
  private static Logger logger = LoggerFactory.getLogger(DeviceDataController.class);
  private ActionRule actionRule;

  public DeviceAction(DeviceActionRepository deviceActionRepository) {
    this.deviceActionRepository = deviceActionRepository;
  }


  @Override
  public void execute(ActionRule actionRule) {
    this.actionRule = actionRule;
    try {
      JSONObject jsonObject = new JSONObject(actionRule.getActionSpecification());
      String deviceUuid = jsonObject.getString("uuId");
      String data = jsonObject.getString("data");
      deviceActionRepository.save(DeviceActionData.builder().uuId(deviceUuid).data(data).timestamp(LocalDateTime.now())
              .status(Status.WAITING).build());
    } catch (JSONException e) {
      logger.error(e.getLocalizedMessage());
    }
  }

  @Override
  public String getType() {
    return actionRule.getAction().getType();
  }
}
