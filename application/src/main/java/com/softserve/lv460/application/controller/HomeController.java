package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.home.HomeRequest;
import com.softserve.lv460.application.dto.home.HomeResponse;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.service.HomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/homes")
@CrossOrigin
public class HomeController {

  private HomeService homeService;

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
  public void update(@RequestBody Home request) {
    homeService.update(request);
  }

  @DeleteMapping("/{home_id}")
  public void delete(@PathVariable("home_id") Long id) {
    homeService.delete(id);
  }

  @GetMapping("/{home_id}")
  public  HomeResponse findOne(@PathVariable("home_id") Long id){
    return homeService.findOneResponse(id);
  }
}
