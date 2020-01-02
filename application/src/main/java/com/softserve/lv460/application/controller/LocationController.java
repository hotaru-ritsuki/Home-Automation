package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.location.LocationRequest;
import com.softserve.lv460.application.dto.location.LocationResponse;
import com.softserve.lv460.application.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@CrossOrigin
public class LocationController {
  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @PostMapping
  public LocationResponse create(@RequestBody LocationRequest request) {
    return locationService.create(request);
  }

  @GetMapping
  public List<LocationResponse> findByAddress(@RequestParam String address){
    return locationService.findByHomeAddress(address);
  }

  @PutMapping
  public void update(@RequestBody LocationRequest request) {
    locationService.update(request);
  }

  @GetMapping("/home")
  public List<LocationResponse> findByHome(@RequestParam Long id) {
    return locationService.findByHome(id);
  }

  @DeleteMapping("/{location_id}")
  public void delete(@PathVariable("location_id") Long id) {
    locationService.delete(id);
  }

  @GetMapping("/{location_id}")
  public LocationResponse findOne(@PathVariable("location_id") Long id) {
    return locationService.findOneResponse(id);
  }

}
