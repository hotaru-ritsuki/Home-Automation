package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.location.LocationRequestDTO;
import com.ritsuki.application.dto.location.LocationResponseDTO;
import com.ritsuki.application.service.LocationService;
import com.ritsuki.application.mapper.location.LocationRequestMapper;
import com.ritsuki.application.mapper.location.LocationResponseMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

  private LocationService locationService;
  private LocationResponseMapper responseMapper;
  private LocationRequestMapper requestMapper;

  @ApiOperation(value = "Create new location")
  @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = LocationResponseDTO.class)
  })
  @PostMapping
  public ResponseEntity<LocationResponseDTO> create(@RequestBody LocationRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toDto(locationService
          .create(requestMapper.toEntity(request))));
  }

  @ApiOperation(value = "Return list of location")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocationResponseDTO.class)
  })
  @GetMapping
  public ResponseEntity<List<LocationResponseDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll().stream()
          .map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Return list of location in home")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocationResponseDTO.class)
  })
  @GetMapping("/home/{id}")
  public ResponseEntity<List<LocationResponseDTO>> findAllByHome(@PathVariable("id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(locationService.findByHome(id).stream()
            .map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Update location")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocationResponseDTO.class)
  })
  @PutMapping
  public ResponseEntity<LocationResponseDTO> update(@RequestBody LocationRequestDTO request) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(locationService
          .update(requestMapper.toEntity(request))));
  }

  @ApiOperation(value = "Delete location")
  @ApiResponses(value = {
        @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
  })
  @DeleteMapping("/{location_id}")
  public ResponseEntity<Void> delete(@PathVariable("location_id") Long id) {
    locationService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @ApiOperation(value = "Return location by id")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocationResponseDTO.class)
  })
  @GetMapping("/{location_id}")
  public ResponseEntity<LocationResponseDTO> findOne(@PathVariable("location_id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(locationService.findOne(id)));
  }

}
