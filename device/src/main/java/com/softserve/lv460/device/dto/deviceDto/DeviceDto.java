package com.softserve.lv460.device.dto.deviceDto;

import com.softserve.lv460.device.dto.locationDto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
  private String uuid;
  private LocationDto location;
  private Object supportedDevice;
}
