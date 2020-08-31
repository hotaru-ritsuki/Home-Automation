package com.ritsuki.device.action;

import com.ritsuki.device.config.cache.DeviceCacheConfig;
import com.ritsuki.device.document.AlertsList;
import com.ritsuki.device.dto.device.LocalDeviceDto;
import com.ritsuki.device.repository.AlertListRepository;
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
    String data = actionData.get("data");
    String description = actionData.get("description");
    try {
      LocalDeviceDto fromCache = deviceCacheConfig.getFromCache(deviceUuid);
      alertListRepository.save(AlertsList.builder().uuId(deviceUuid).data(data).timestamp(LocalDateTime.now())
              .description(description).homeId(fromCache.getHomeId()).build());
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getType() {
    return "ALERT";
  }
}
