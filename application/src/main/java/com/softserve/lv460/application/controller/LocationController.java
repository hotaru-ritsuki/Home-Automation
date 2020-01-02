package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.location.LocationRequest;
import com.softserve.lv460.application.dto.location.LocationResponse;
import com.softserve.lv460.application.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin
public class LocationController {
  private final LocationService locationService;

  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  public List<LocationResponse> findByAddress(@RequestParam String address){
    return locationService.findByHomeAddress(address);
  }

  @PostMapping
  public void create(@RequestBody LocationRequest request) {
    locationService.create(request);
  }

  @PutMapping
  public void update(@RequestParam Long id, @RequestBody LocationRequest request) {
    locationService.update(id, request);
  }


  @DeleteMapping
  public void delete(@RequestParam Long id) {
    locationService.delete(id);
  }

  @GetMapping("/home")
  public List<LocationResponse> findByHome(@RequestParam Long id) {
    return locationService.findByHome(id);
  }

}
