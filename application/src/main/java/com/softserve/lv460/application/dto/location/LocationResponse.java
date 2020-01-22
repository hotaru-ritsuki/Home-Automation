package com.softserve.lv460.application.dto.location;

import com.softserve.lv460.application.entity.LocalDevice;
import lombok.Data;

import java.util.List;

@Data
public class LocationResponse {
  private Long id;
  private String name;
  private List<LocalDevice> localDevices;
}
