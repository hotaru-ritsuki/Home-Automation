package com.softserve.lv460.device.dto.AlertList;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class AlertListDto {

  private String uuid;
  private String data;
  private LocalDateTime timeStamp;
}
