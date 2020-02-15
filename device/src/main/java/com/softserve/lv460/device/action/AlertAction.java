package com.softserve.lv460.device.action;

import com.softserve.lv460.device.document.AlertsList;
import com.softserve.lv460.device.dto.enums.Status;
import com.softserve.lv460.device.repositiry.AlertListRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class AlertAction implements Action {
  private final AlertListRepository alertListRepository;

  @Override
  public void execute(Map<String, String> actionData) {
    String deviceUuid = actionData.get("uuId");
    String data = actionData.get("data");
    alertListRepository.save(AlertsList.builder().uuId(deviceUuid).data(data).timestamp(LocalDateTime.now())
        .status(Status.WAITING).build());
  }

  @Override
  public String getType() {
    return null;
  }
}
