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

  @PutMapping("/{id}")
  public void update(@PathVariable("id") Long id, @RequestBody LocationRequest request) {
    locationService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") Long id) {
    locationService.delete(id);
  }

  @GetMapping("/home")
  public List<LocationResponse> findByHome(@RequestParam Long id) {
    return locationService.findByHome(id);
  }

}
