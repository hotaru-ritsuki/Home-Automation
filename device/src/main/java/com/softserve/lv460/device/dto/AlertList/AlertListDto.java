package com.softserve.lv460.device.dto.AlertList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertListDto {

  //private String uuid;
  private String data;
  private LocalDateTime timeStamp;
}
