package com.softserve.lv460.device.dto.rule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceActionDataDto {
  private String data;
  private LocalDateTime timestamp;
}
