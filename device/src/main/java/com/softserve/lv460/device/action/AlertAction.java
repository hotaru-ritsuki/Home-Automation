package com.softserve.lv460.device.action;

import com.softserve.lv460.device.config.cache.DeviceCacheConfig;
import com.softserve.lv460.device.document.AlertsList;
import com.softserve.lv460.device.dto.device.LocalDeviceDto;
import com.softserve.lv460.device.dto.enums.Status;
import com.softserve.lv460.device.repositiry.AlertListRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Data
@AllArgsConstructor
public class AlertAction implements Action {
  private final AlertListRepository alertListRepository;
  private final DeviceCacheConfig deviceCacheConfig;

  @Override
  public void execute(Map<String, String> actionData) {
    String deviceUuid = actionData.get("uuid");
    try {
      System.out.println("AlertAction try");
      final LocalDeviceDto fromCache = deviceCacheConfig.getFromCache(deviceUuid);
      System.out.println(fromCache);
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    String data = actionData.get("data");
    alertListRepository.save(AlertsList.builder().uuId(deviceUuid).data(data).timestamp(LocalDateTime.now())
        .status(Status.WAITING).build());
    System.out.println("saving data");
  }

  @Override
  public String getType() {
    return "ALERT";
  }
}
