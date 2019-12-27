package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.home.HomeRequest;
import com.softserve.lv460.application.dto.home.HomeResponse;
import com.softserve.lv460.application.dto.location.LocationResponse;
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

  public Home create(HomeRequest request) {
    Optional<Home> isHome = homeRepository.findByAddressaLike(request.getAddressa());
    if (!isHome.isPresent()) {
      return homeRepository.save(requestToHome(new Home(), request));
    }
    throw new RuntimeException("Home with address " + request.getAddressa() + " is already register");
  }

  public List<HomeResponse> findAll() {
    List<Home> all = homeRepository.findAll();
    List<HomeResponse> responses = new ArrayList<>();
    for (Home home : all) {
      responses.add(homeToResponse(home));
    }
    return responses;
  }

  public Home findOne(Long id) {
    return homeRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Home with id " + id + " not exists"));
  }

  public HomeResponse findOneResponse(Long id) {
    return homeToResponse(findOne(id));
  }

  public Home update(HomeRequest request) {
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

  public HomeResponse homeToResponse(Home home) {
    HomeResponse response = new HomeResponse();
    response.setId(home.getId());
    response.setCountry(home.getCountry());
    response.setCity(home.getCity());
    response.setAddressa(home.getAddressa());
    List<LocationResponse> locationHome = locationService.findByHome(home.getId());
    if (!locationHome.isEmpty()) {
      response.setLocations(locationHome);
    }
    return response;
  }

  private Home requestToHome(Home home, HomeRequest request) {
    home.setCountry(request.getCountry());
    home.setCity(request.getCity());
    home.setAddressa(request.getAddressa());
    return home;
  }

}
