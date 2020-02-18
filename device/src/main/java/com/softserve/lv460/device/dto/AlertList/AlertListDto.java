package com.softserve.lv460.device.dto.AlertList;

import com.softserve.lv460.device.constant.ValidationConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
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
