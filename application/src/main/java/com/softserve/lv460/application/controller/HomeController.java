package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.home.HomeRequestDTO;
import com.softserve.lv460.application.dto.home.HomeResponseDTO;
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
  public HomeResponseDTO create(@RequestBody HomeRequestDTO request) {
    return homeService.create(request);
  }

  @GetMapping
  public List<HomeResponseDTO> findAll() {
    return homeService.findAll();
  }

  @PutMapping
  public void update(@RequestBody HomeRequestDTO request) {
    homeService.update(request);
  }

  @DeleteMapping("/{home_id}")
  public void delete(@PathVariable("home_id") Long id) {
    homeService.delete(id);
  }

  @GetMapping("/{home_id}")
  public HomeResponseDTO findOne(@PathVariable("home_id") Long id) {
    return homeService.findOneResponse(id);
  }
}
