package com.ritsuki.device.dto.AlertList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertListDto {
  private String data;
  private String id;
  private String uuId;
  private LocalDateTime timestamp;
  private String description;
  private Long homeId;
}
