package com.softserve.lv460.application.dto.localDevice;

import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.softserve.lv460.application.dto.location.LocationResponseDTO;
import lombok.Data;

@Data
public class LocalDeviceResponseDTO {
  private String uuid;
  private String description;
  private LocationResponseDTO location;
  private DeviceTemplateResponseDTO deviceTemplate;
}
