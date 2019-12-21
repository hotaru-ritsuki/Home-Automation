package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.location.LocationRequest;
import com.softserve.lv460.application.dto.location.LocationResponse;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.repository.HomeRepository;
import com.softserve.lv460.application.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
  @Autowired
  private LocationRepository locationRepository;
  @Autowired
  private HomeRepository homeRepository;


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

  public Location update(Long id, LocationRequest lR) {
    Location location = findOne(id);
    location.setName(lR.getName());
    return locationRepository.save(location);
  }

  public void delete(Long id) {
    Location location = findOne(id);
    locationRepository.delete(location);
  }

  public List<LocationResponse> findByHome(Long id) {
    List<Location> all = locationRepository.findAll();
    List<LocationResponse> lr = new ArrayList<>();
    for (Location location : all) {
      if (location.getHome().getId() == id) {
        lr.add(locationToResponse(location));
      }
    }
    return lr;
  }

  public static LocationResponse locationToResponse(Location location) {
    LocationResponse lR = new LocationResponse();
    lR.setId(location.getId());
    lR.setName(location.getName());
    return lR;
  }

}
