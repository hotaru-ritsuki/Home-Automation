package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateFilterDTO;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateRequestDTO;
import com.ritsuki.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.ritsuki.application.service.DeviceTemplateService;
import com.ritsuki.application.dto.data.DataResponse;
import com.ritsuki.application.mapper.deviceTemplate.DeviceTemplateRequestMapper;
import com.ritsuki.application.mapper.deviceTemplate.DeviceTemplateResponseMapper;
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

@AllArgsConstructor
@RestController
@RequestMapping("/devices")
@CrossOrigin
public class DeviceTemplateController {
  private final DeviceTemplateService deviceTemplateService;
  private final DeviceTemplateRequestMapper requestMapper;
  private final DeviceTemplateResponseMapper responseMapper;

  @Operation(summary = "Find all device templates")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = String.class)))
  })
  @GetMapping
  public ResponseEntity<List<DeviceTemplateResponseDTO>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAll()
            .stream().map(responseMapper::toDto).collect(Collectors.toList()));
  }

  @Operation(summary = "Find all device template's models")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = String.class)))
  })
  @GetMapping("/models")
  public ResponseEntity<List<String>> findAllModels() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllModels());
  }

  @Operation(summary = "Find all device template's types")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = String.class)))
  })
  @GetMapping("/types")
  public ResponseEntity<List<String>> findAllTypes() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllTypes());
  }

  @Operation(summary = "Find all device template's brands")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = String.class)))
  })
  @GetMapping("/brands")
  public ResponseEntity<List<String>> findAllBrands() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllBrands());
  }

  @Operation(summary = "Find all device template's release years")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = Integer.class)))
  })
  @GetMapping("/years")
  public ResponseEntity<List<Integer>> findAllReleaseYears() {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllReleaseYears());
  }

  @Operation(summary = "Update device template")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = DeviceTemplateResponseDTO.class)))
  })
  @PutMapping
  public ResponseEntity<DeviceTemplateResponseDTO> update(@RequestBody DeviceTemplateRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(responseMapper.toDto(deviceTemplateService.update(requestMapper.toEntity(dto))));
  }

  @Operation(summary = "Create new device template")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = DeviceTemplateResponseDTO.class)))
  })
  @PostMapping
  public ResponseEntity<DeviceTemplateResponseDTO> save(@RequestBody DeviceTemplateRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(responseMapper.toDto(deviceTemplateService.save(requestMapper.toEntity(dto))));
  }

  @Operation(summary = "Delete device template by id")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Long> delete(@PathVariable(name = "id") Long id) {
    deviceTemplateService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @Operation(summary = "Find device templates using filter request")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = DataResponse.class)))
  })
  @GetMapping("/filter/page={page}")
  public ResponseEntity<DataResponse<DeviceTemplateResponseDTO>> findAllByFilter(@PathVariable(name = "page") Integer page,
                                                                                 DeviceTemplateFilterDTO request) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllByFilter(page, request));
  }
}
