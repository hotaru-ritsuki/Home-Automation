package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.feature.FeatureDTO;
import com.softserve.lv460.application.entity.Feature;
import com.softserve.lv460.application.mapper.feature.FeatureMapper;
import com.softserve.lv460.application.service.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/features")
@CrossOrigin
public class FeatureController {

  private FeatureService featureService;
  private FeatureMapper mapper;

  @PostMapping
  public FeatureDTO create(@RequestBody FeatureDTO dto) {
    Feature feature = mapper.toEntity(dto);
    Feature featureCreated = featureService.create(feature);
    return mapper.toDto(featureCreated);
  }

  @GetMapping
  public List<FeatureDTO> findAll() {
    List<Feature> allFeatures = featureService.findAll();
    return allFeatures.stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @PutMapping
  public FeatureDTO update(@RequestBody FeatureDTO dto) {
    Feature feature = mapper.toEntity(dto);
    Feature featureUpdated = featureService.update(feature);
    return mapper.toDto(featureUpdated);
  }

  @DeleteMapping
  public void delete(@RequestParam Long id) {
    featureService.delete(id);
  }
}
