package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.home.HomeRequest;
import com.softserve.lv460.application.dto.home.HomeResponse;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.service.HomeService;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
>>>>>>> 5a7092ed... dashboard added, home search via address added
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
  public HomeResponse create(@RequestBody HomeRequest request) {
    return homeService.create(request);
  }

  @GetMapping
  public List<HomeResponse> findAll() {
    return homeService.findAll();
  }

  @PutMapping
  public void update(@RequestBody HomeRequest request) {
    homeService.update(request);
  }

  @DeleteMapping("/{home_id}")
  public void delete(@PathVariable("home_id") Long id) {
    homeService.delete(id);
  }

<<<<<<< HEAD
  @GetMapping("/{home_id}")
  public HomeResponse findOne(@PathVariable("home_id") Long id) {
    return homeService.findOneResponse(id);
=======

  @GetMapping("/find")
  public Home findByAddress(@RequestParam String address, Model model) {
    try {
      return homeService.findHomeByAddress(address);
    } catch (IllegalArgumentException e) {
      model.addAttribute("error-message", "There is no such home for address " + address);
    }
    return new Home();
>>>>>>> 5a7092ed... dashboard added, home search via address added
  }
}
