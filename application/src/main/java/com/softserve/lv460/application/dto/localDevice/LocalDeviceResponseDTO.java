package com.softserve.lv460.application.dto.localDevice;

import com.softserve.lv460.application.dto.location.LocationResponseDTO;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
<<<<<<< HEAD
import com.softserve.lv460.application.dto.location.LocationResponseDTO;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.repository.LocationRepository;
=======

>>>>>>> a582b2954bd96a2f92701914f3ec192e814e310d
import lombok.Data;

@Data
public class LocalDeviceResponseDTO {
<<<<<<< HEAD
  private String uuid;
  private String description;
  private LocationResponseDTO location;
  private DeviceTemplateResponseDTO deviceTemplate;
=======
    private String uuid;
    private String description;
    private LocationResponseDTO location;
    private DeviceTemplateResponseDTO deviceTemplate;
>>>>>>> a582b2954bd96a2f92701914f3ec192e814e310d
}
