package com.ritsuki.application.dto.localDevice;

import com.ritsuki.application.dto.location.LocationResponseDTO;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import lombok.Data;

@Data
public class LocalDeviceRespDTO {
  private String uuid;
  private String description;
  private Long homeId;
  private LocationResponseDTO location;
  private DeviceTemplateResponseDTO deviceTemplate;

}
