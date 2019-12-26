package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.location.LocationRequest;
import com.softserve.lv460.application.dto.location.LocationResponse;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.repository.HomeRepository;
import com.softserve.lv460.application.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LocationService {

  private LocationRepository locationRepository;
  private HomeRepository homeRepository;

  public static LocationResponse locationToResponse(Location location) {
    LocationResponse response = new LocationResponse();
    response.setId(location.getId());
    response.setName(location.getName());
    return response;
  }

  public Location create(LocationRequest lR) {
    Location location = new Location();
    location.setName(lR.getName());
    location.setHome(homeRepository.findById(lR.getHomeId()).get());
    return locationRepository.save(location);
  }

  public List<LocationResponse> findAll() {
    return locationRepository.findAll().stream().map(LocationService::locationToResponse).collect(Collectors.toList());
  }

  public Location findOne(Long id) {
    return locationRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Home with id " + id + " not exists"));
  }

  public LocationResponse findOneResponse(Long id) {
    return locationToResponse(findOne(id));
  }

  public Location update(Long id, LocationRequest request) {
    Location location = findOne(id);
    location.setName(request.getName());
    return locationRepository.save(location);
  }

  public void delete(Long id) {
    Location location = findOne(id);
    locationRepository.delete(location);
  }

  public List<LocationResponse> findByHome(Long id) {
    List<Location> all = locationRepository.findAll();
    List<LocationResponse> responses = new ArrayList<>();
    for (Location location : all) {
      if (location.getHome().getId() == id) {
        responses.add(locationToResponse(location));
      }
    }
    return responses;
  }

}