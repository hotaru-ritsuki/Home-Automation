package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.feature.FeatureDTO;
import com.softserve.lv460.application.entity.Feature;
import com.softserve.lv460.application.mapper.feature.FeatureMapper;
import com.softserve.lv460.application.service.FeatureService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

  @ApiOperation(value = "Create new feature")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = HttpStatuses.CREATED, response = FeatureDTO.class)
  })
  @PostMapping
  public FeatureDTO create(@RequestBody FeatureDTO dto) {
    Feature feature = mapper.toEntity(dto);
    Feature featureCreated = featureService.create(feature);
    return mapper.toDto(featureCreated);
  }

  @ApiOperation(value = "List of all features")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = HttpStatuses.OK, response = FeatureDTO.class)
  })
  @GetMapping
  public List<FeatureDTO> findAll() {
    List<Feature> allFeatures = featureService.findAll();
    return allFeatures.stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @ApiOperation(value = "Update feature")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = HttpStatuses.OK, response = FeatureDTO.class)
  })
  @PutMapping
  public FeatureDTO update(@RequestBody FeatureDTO dto) {
    Feature feature = mapper.toEntity(dto);
    Feature featureUpdated = featureService.update(feature);
    return mapper.toDto(featureUpdated);
  }

  @ApiOperation(value = "Delete feature by id")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
      @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping
  public void delete(@RequestParam Long id) {
    featureService.delete(id);
  }
}
