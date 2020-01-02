package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.location.LocationRequest;
import com.softserve.lv460.application.dto.location.LocationResponse;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.repository.HomeRepository;
import com.softserve.lv460.application.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LocationService {

  private LocationRepository locationRepository;
  private HomeRepository homeRepository;

  public LocationResponse create(LocationRequest request) {
    Location location = new Location();
    location.setName(request.getName());
    location.setHome(homeRepository.findById(request.getHomeId()).get());
    return locationToResponse(locationRepository.save(location));
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

  public Location update(LocationRequest update) {
    Location location = findOne(update.getId());
    location.setName(update.getName());
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

  public List<LocationResponse> findByHomeAddress(String address){
    List<Location> all = locationRepository.findAll();
    List<LocationResponse> lr = new ArrayList<>();
    for (Location location : all) {
      if (location.getHome().getAddressa().equals(address)) {
        lr.add(locationToResponse(location));
      }
    }
    return lr;
  }

  public static LocationResponse locationToResponse(Location location) {
    LocationResponse lR = new LocationResponse();
    lR.setId(location.getId());
    lR.setName(location.getName());
    lR.setDevises(new ArrayList<>(location.getDevices()));
    return lR;
  }
}
