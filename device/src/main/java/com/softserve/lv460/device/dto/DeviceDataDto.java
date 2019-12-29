package com.softserve.lv460.device.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class DeviceDataDto {
  private Map<String,String> data;
  private LocalDateTime timeStamp;
}
