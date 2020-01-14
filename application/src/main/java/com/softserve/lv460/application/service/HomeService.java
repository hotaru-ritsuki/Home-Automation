package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.home.HomeRequestDTO;
import com.softserve.lv460.application.dto.home.HomeResponseDTO;
import com.softserve.lv460.application.dto.location.LocationResponseDTO;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.repository.HomeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HomeService {

  private HomeRepository homeRepository;
  private LocationService locationService;

  public HomeResponseDTO create(HomeRequestDTO request) {
    Optional<Home> isHome = homeRepository.findByAddressaLike(request.getAddressa());
    if (!isHome.isPresent()) {
      return homeToResponse(homeRepository.save(requestToHome(new Home(), request)));
    }
    throw new RuntimeException("Home with address " + request.getAddressa() + " is already registered");
  }

  public List<HomeResponseDTO> findAll() {
    List<Home> all = homeRepository.findAll();
    List<HomeResponseDTO> responses = new ArrayList<>();
    for (Home home : all) {
      responses.add(homeToResponse(home));
    }
    return responses;
  }

  public Home findOne(Long id) {
    return homeRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Home with id " + id + " does not exist"));
  }

  public HomeResponseDTO findOneResponse(Long id) {
    return homeToResponse(findOne(id));
  }

  public Home update(HomeRequestDTO request) {
    Home home = findOne(request.getId());
    home.setCountry(request.getCountry());
    home.setCity(request.getCity());
    home.setAddressa(request.getAddressa());
    return homeRepository.save(home);

  }

  public void delete(Long id) {
    Home home = findOne(id);
    if (locationService.findByHome(id).isEmpty()) {
      homeRepository.delete(home);
    } else {
      throw new RuntimeException("Home with id " + id + " has dependencies");
    }
  }

  public HomeResponseDTO homeToResponse(Home home) {
    HomeResponseDTO response = new HomeResponseDTO();
    response.setId(home.getId());
    response.setCountry(home.getCountry());
    response.setCity(home.getCity());
    response.setAddressa(home.getAddressa());
    List<LocationResponseDTO> locationHome = locationService.findByHome(home.getId());
    if (!locationHome.isEmpty()) {
      response.setLocations(locationHome);
    }
    return response;
  }

  private Home requestToHome(Home home, HomeRequestDTO request) {
    home.setCountry(request.getCountry());
    home.setCity(request.getCity());
    home.setAddressa(request.getAddressa());
    return home;
  }

}
