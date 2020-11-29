package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateFilterDTO;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateRequestDTO;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.ritsuki.application.service.DeviceTemplateService;
import com.ritsuki.application.dto.data.DataResponse;
import com.ritsuki.application.mapper.deviceTemplate.DeviceTemplateRequestMapper;
import com.ritsuki.application.mapper.deviceTemplate.DeviceTemplateResponseMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/devices")
@CrossOrigin
public class DeviceTemplateController {
  private final DeviceTemplateService deviceTemplateService;
  private final DeviceTemplateRequestMapper requestMapper;
  private final DeviceTemplateResponseMapper responseMapper;

  @ApiOperation(value = "Find all device templates")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = String.class)
  })
  @GetMapping
  public ResponseEntity<List<DeviceTemplateResponseDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAll()
            .stream().map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @ApiOperation(value = "Find all device template's models")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = String.class)
  })
  @GetMapping("/models")
  public ResponseEntity<List<String>> findAllModels() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllModels());
  }

  @ApiOperation(value = "Find all device template's types")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = String.class)
  })
  @GetMapping("/types")
  public ResponseEntity<List<String>> findAllTypes() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllTypes());
  }

  @ApiOperation(value = "Find all device template's brands")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = String.class)
  })
  @GetMapping("/brands")
  public ResponseEntity<List<String>> findAllBrands() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllBrands());
  }

  @ApiOperation(value = "Find all device template's release years")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = Integer.class)
  })
  @GetMapping("/years")
  public ResponseEntity<List<Integer>> findAllReleaseYears() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllReleaseYears());
  }

  @ApiOperation(value = "Update device template")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = DeviceTemplateResponseDTO.class)
  })
  @PutMapping
  public ResponseEntity<DeviceTemplateResponseDTO> update(@RequestBody DeviceTemplateRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(responseMapper.toDto(deviceTemplateService.update(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Create new device template")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = DeviceTemplateResponseDTO.class)
  })
  @PostMapping
  public ResponseEntity<DeviceTemplateResponseDTO> save(@RequestBody DeviceTemplateRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(responseMapper.toDto(deviceTemplateService.save(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Delete device template by id")
  @ApiResponses(value = {
          @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Long> delete(@PathVariable(name = "id") Long id) {
    deviceTemplateService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @ApiOperation(value = "Find device templates using filter request")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = DataResponse.class)
  })
  @GetMapping("/filter/page={page}")
  public ResponseEntity<DataResponse<DeviceTemplateResponseDTO>> findAllByFilter(@PathVariable(name = "page") Integer page,
                                                                                 DeviceTemplateFilterDTO request) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllByFilter(page, request));
  }
}
