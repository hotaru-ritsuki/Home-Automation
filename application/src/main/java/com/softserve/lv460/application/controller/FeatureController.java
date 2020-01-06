package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.feature.FeatureRequest;
import com.softserve.lv460.application.dto.feature.FeatureResponse;
import com.softserve.lv460.application.service.FeatureService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/features")
@CrossOrigin
public class FeatureController {

  private FeatureService featureService;

  public FeatureController(FeatureService featureService) {
    this.featureService = featureService;
  }

  @PostMapping
  public void create(@RequestBody FeatureRequest request) {
    featureService.create(request);
  }

  @GetMapping
  public List<FeatureResponse> findAll() {
    return featureService.findAll();
  }

  @PutMapping("/{id}")
  public void update(@RequestParam Long id, @RequestBody FeatureRequest request) {
    featureService.update(id, request);
  }

  @DeleteMapping("/{id}")
  public void delete(@RequestParam Long id) {
    featureService.delete(id);
  }
}
