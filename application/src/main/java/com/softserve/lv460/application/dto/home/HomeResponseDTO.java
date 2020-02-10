package com.softserve.lv460.application.dto.home;

import com.softserve.lv460.application.dto.location.LocationResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class HomeResponseDTO {
  private Long id;
  private String country;
  private String city;
  private String addressa;
  private String name;
  private List<LocationResponseDTO> locations;
}
