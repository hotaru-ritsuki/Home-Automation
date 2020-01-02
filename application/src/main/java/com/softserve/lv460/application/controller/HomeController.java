package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.home.HomeRequest;
import com.softserve.lv460.application.dto.home.HomeResponse;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.service.HomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/home")
@CrossOrigin
public class HomeController {
  private final HomeService homeService;

  public HomeController(HomeService homeService) {
    this.homeService = homeService;
  }

  @PostMapping
  public void create(@RequestBody HomeRequest request) {
    homeService.create(request);
  }

  @GetMapping
  public List<HomeResponse> findAll() {
    return homeService.findAll();
  }

  @PutMapping
  public void update(@RequestParam Long id, @RequestBody HomeRequest request) {
    homeService.update(id, request);
  }

  @DeleteMapping
  public void delete(@RequestParam Long id) {
    homeService.delete(id);
  }


  @GetMapping("/find")
  public Home findByAddress(@RequestParam String address) {
    return homeService.findHomeByAddress(address);
  }
}
