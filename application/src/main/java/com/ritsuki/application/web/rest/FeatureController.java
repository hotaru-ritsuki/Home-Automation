package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.feature.FeatureDTO;
import com.ritsuki.application.service.FeatureService;
import com.ritsuki.application.entity.Feature;
import com.ritsuki.application.mapper.feature.FeatureMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/features")
@CrossOrigin
public class FeatureController {

  private final FeatureService featureService;
  private final FeatureMapper mapper;

  @Operation(summary = "Create new feature")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = FeatureDTO.class)))
  })
  @PostMapping
  public FeatureDTO create(@RequestBody FeatureDTO dto) {
    Feature feature = mapper.toEntity(dto);
    Feature featureCreated = featureService.create(feature);
    return mapper.toDto(featureCreated);
  }

  @Operation(summary = "List of all features")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = FeatureDTO.class)))
  })
  @GetMapping
  public List<FeatureDTO> findAll() {
    List<Feature> allFeatures = featureService.findAll();
    return allFeatures.stream().map(mapper::toDto).collect(Collectors.toList());
  }

  @Operation(summary = "Update feature")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = FeatureDTO.class)))
  })
  @PutMapping
  public FeatureDTO update(@RequestBody FeatureDTO dto) {
    Feature feature = mapper.toEntity(dto);
    Feature featureUpdated = featureService.update(feature);
    return mapper.toDto(featureUpdated);
  }

  @Operation(summary = "Delete feature by id")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
      @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping
  public void delete(@RequestParam Long id) {
    featureService.delete(id);
  }
}
