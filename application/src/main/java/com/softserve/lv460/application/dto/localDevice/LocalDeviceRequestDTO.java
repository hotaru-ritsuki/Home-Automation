package com.softserve.lv460.application.dto.localDevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDeviceRequestDTO {
  private String uuid;
  private String description;
  private Long locationId;
  private Long deviceTemplateId;
}
