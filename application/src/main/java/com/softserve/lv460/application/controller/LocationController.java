package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.location.LocationRequest;
import com.softserve.lv460.application.dto.location.LocationResponse;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@CrossOrigin
public class LocationController {

  private LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @PostMapping
  public void create(@RequestBody LocationRequest request) {
    locationService.create(request);
  }

  @GetMapping
  public List<LocationResponse> findAll() {
    return locationService.findAll();
  }

  @PutMapping
  public void update(@RequestBody Location request) {
    locationService.update(request);
  }

  @DeleteMapping("/{location_id}")
  public void delete(@PathVariable("location_id") Long id) {
    locationService.delete(id);
  }

  @GetMapping("/{home_id}")
  public List<LocationResponse> findByHome(@PathVariable("home_id") Long id) {
    return locationService.findByHome(id);
  }

  @GetMapping("/{location_id}")
  public  LocationResponse findOne(@PathVariable("location_id") Long id){
    return locationService.findOneResponse(id);
  }

}
