package com.softserve.lv460.device.dto.device;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class DeviceDataDto {
  private String uuid;
  private Map<String, String> data;
  private LocalDateTime timeStamp;
}
