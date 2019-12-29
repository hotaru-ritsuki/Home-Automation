package com.softserve.lv460.application.dto.home;

import com.softserve.lv460.application.dto.location.LocationResponse;
import lombok.Data;

import java.util.List;

@Data
public class HomeResponse {
  private Long id;
  private String country;
  private String city;
  private String addressa;
  private List<LocationResponse> locations;
}
