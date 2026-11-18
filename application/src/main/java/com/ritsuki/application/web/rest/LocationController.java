package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.location.LocationRequestDTO;
import com.ritsuki.application.dto.location.LocationResponseDTO;
import com.ritsuki.application.service.LocationService;
import com.ritsuki.application.mapper.location.LocationRequestMapper;
import com.ritsuki.application.mapper.location.LocationResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
@CrossOrigin
@AllArgsConstructor
public class LocationController {

  private final LocationService locationService;
  private final LocationResponseMapper responseMapper;
  private final LocationRequestMapper requestMapper;

  @Operation(summary = "Create new location")
  @ApiResponses({
        @ApiResponse(responseCode = "201", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = LocationResponseDTO.class)))
  })
  @PostMapping
  public ResponseEntity<LocationResponseDTO> create(@RequestBody LocationRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toDto(locationService
          .create(requestMapper.toEntity(request))));
  }

  @Operation(summary = "Return list of location")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocationResponseDTO.class)))
  })
  @GetMapping
  public ResponseEntity<List<LocationResponseDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll().stream()
          .map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @Operation(summary = "Return list of location in home")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocationResponseDTO.class)))
  })
  @GetMapping("/home/{id}")
  public ResponseEntity<List<LocationResponseDTO>> findAllByHome(@PathVariable("id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(locationService.findByHome(id).stream()
            .map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @Operation(summary = "Update location")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocationResponseDTO.class)))
  })
  @PutMapping
  public ResponseEntity<LocationResponseDTO> update(@RequestBody LocationRequestDTO request) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(locationService
          .update(requestMapper.toEntity(request))));
  }

  @Operation(summary = "Delete location")
  @ApiResponses({
        @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
        @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
  })
  @DeleteMapping("/{location_id}")
  public ResponseEntity<Void> delete(@PathVariable("location_id") Long id) {
    locationService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Return location by id")
  @ApiResponses({
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = LocationResponseDTO.class)))
  })
  @GetMapping("/{location_id}")
  public ResponseEntity<LocationResponseDTO> findOne(@PathVariable("location_id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(locationService.findOne(id)));
  }

}
