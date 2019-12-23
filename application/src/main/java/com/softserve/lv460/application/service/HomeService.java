package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.home.HomeRequest;
import com.softserve.lv460.application.dto.home.HomeResponse;
import com.softserve.lv460.application.dto.location.LocationResponse;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {
  @Autowired
  private HomeRepository homeRepository;
  @Autowired
  private LocationService locationService;

  public Home create(HomeRequest hR) {
    Home home = new Home();
    home.setCountry(hR.getCountry());
    home.setCity(hR.getCity());
    home.setAddressa(hR.getAddressa());
    return homeRepository.save(home);
  }

  public List<HomeResponse> findAll() {
    List<Home> all = homeRepository.findAll();
    List<HomeResponse> responses=new ArrayList<>();
    for (Home home : all) {
      responses.add(homeToResponse(home));
    }
    return responses;
  }

  public Home findOne(Long id) {
    return homeRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Home with id " + id + " not exists"));
  }

  public HomeResponse findOneResponse(Long id){
    return homeToResponse(findOne(id));
  }

  public Home update(Long id, HomeRequest hR) {
    Home home = findOne(id);
    home.setCountry(hR.getCountry());
    home.setCity(hR.getCity());
    home.setAddressa(hR.getAddressa());
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

  public  HomeResponse homeToResponse(Home home) {
    HomeResponse hR = new HomeResponse();
    hR.setId(home.getId());
    hR.setCountry(home.getCountry());
    hR.setCity(home.getCity());
    hR.setAddressa(home.getAddressa());
    List<LocationResponse> locationHome = locationService.findByHome(home.getId());
    if (!locationHome.isEmpty()) {
      hR.setLocations(locationHome);
    }
    return hR;
  }

  public Home findHomeByAddress(String address){
    return homeRepository.findByAddressa(address).orElseThrow(() -> new IllegalArgumentException("There is no such" +
      "home with address " + address));
  }

}
