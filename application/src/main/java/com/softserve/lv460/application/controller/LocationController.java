package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.location.LocationRequestDTO;
import com.softserve.lv460.application.dto.location.LocationResponseDTO;
import com.softserve.lv460.application.mapper.location.LocationRequestMapper;
import com.softserve.lv460.application.mapper.location.LocationResponseMapper;
import com.softserve.lv460.application.service.LocationService;
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
        @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = LocationResponseDTO.class),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @PostMapping
  public ResponseEntity<LocationResponseDTO> create(@RequestBody LocationRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toDto(locationService.create(requestMapper.toEntity(request))));
  }

  @ApiOperation(value = "Return list of location")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocationResponseDTO.class),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @GetMapping
  public ResponseEntity<List<LocationResponseDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(locationService.findAll().stream().map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Update location")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocationResponseDTO.class),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @PutMapping
  public ResponseEntity<LocationResponseDTO> update(@RequestBody LocationRequestDTO request) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(locationService.update(requestMapper.toEntity(request))));
  }

  @ApiOperation(value = "Delete location")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = Long.class),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
  })
  @DeleteMapping("/{location_id}")
  public Long delete(@PathVariable("location_id") Long id) {
    return locationService.delete(id);
  }

  @ApiOperation(value = "Return location by id")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = LocationResponseDTO.class),
        @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
        @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @GetMapping("/{location_id}")
  public ResponseEntity<LocationResponseDTO> findOne(@PathVariable("location_id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(locationService.findOne(id)));
  }

}
