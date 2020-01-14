package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.location.LocationRequestDTO;
import com.softserve.lv460.application.dto.location.LocationResponseDTO;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.repository.HomeRepository;
import com.softserve.lv460.application.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LocationService {

  private LocationRepository locationRepository;
  private HomeRepository homeRepository;

  public static LocationResponseDTO locationToResponse(Location location) {
    LocationResponseDTO response = new LocationResponseDTO();
    response.setId(location.getId());
    response.setName(location.getName());
    return response;
  }

  public LocationResponseDTO create(LocationRequestDTO request) {
    Location location = new Location();
    location.setName(request.getName());
    location.setHome(homeRepository.findById(request.getHomeId()).get());
    return locationToResponse(locationRepository.save(location));
  }

  public List<LocationResponseDTO> findAll() {
    return locationRepository.findAll().stream().map(LocationService::locationToResponse).collect(Collectors.toList());
  }

  public Location findOne(Long id) {
    return locationRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Home with id " + id + " not exists"));
  }

  public LocationResponseDTO findOneResponse(Long id) {
    return locationToResponse(findOne(id));
  }

  public Location update(LocationRequestDTO update) {
    Location location = findOne(update.getId());
    location.setName(update.getName());
    return locationRepository.save(location);
  }

  public void delete(Long id) {
    Location location = findOne(id);
    locationRepository.delete(location);
  }

  public List<LocationResponseDTO> findByHome(Long id) {
    return locationRepository.findAllByHome(homeRepository.findById(id)).stream().map(LocationService::locationToResponse).collect(Collectors.toList());
  }

}
