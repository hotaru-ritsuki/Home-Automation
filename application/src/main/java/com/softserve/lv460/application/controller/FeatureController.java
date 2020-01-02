package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.feature.FeatureRequest;
import com.softserve.lv460.application.dto.feature.FeatureResponse;
import com.softserve.lv460.application.service.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/features")
@CrossOrigin
public class FeatureController {

  private FeatureService featureService;

  @PostMapping
  public void create(@RequestBody FeatureRequest request) {
    featureService.create(request);
  }

  @GetMapping
  public List<FeatureResponse> findAll() {
    return featureService.findAll();
  }

  @PutMapping
  public void update(@RequestBody FeatureRequest request) {
    featureService.update(request.getId(), request);
  }

  @DeleteMapping
  public void delete(@RequestParam Long id) {
    featureService.delete(id);
  }
}
