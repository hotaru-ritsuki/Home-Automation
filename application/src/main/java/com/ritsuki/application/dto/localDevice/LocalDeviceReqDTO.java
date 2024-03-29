package com.ritsuki.application.dto.localDevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDeviceReqDTO {
  private String uuid;
  private String description;
  private Long locationId;
  private Long deviceTemplateId;
}
