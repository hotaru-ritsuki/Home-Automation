package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateFilterDTO;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateRequestDTO;
import com.softserve.lv460.application.dto.deviceTemplate.DeviceTemplateResponseDTO;
import com.softserve.lv460.application.dto.data.DataResponse;
import com.softserve.lv460.application.mapper.deviceTemplate.DeviceTemplateRequestMapper;
import com.softserve.lv460.application.mapper.deviceTemplate.DeviceTemplateResponseMapper;
import com.softserve.lv460.application.service.DeviceTemplateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/devices")
@CrossOrigin
public class DeviceTemplateController {
  private DeviceTemplateService deviceTemplateService;
  private DeviceTemplateRequestMapper requestMapper;
  private DeviceTemplateResponseMapper responseMapper;

  @ApiOperation(value = "Find all device template's brands")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
  })
  @GetMapping("/brands")
  public List<String> findAllBrands() {
    return deviceTemplateService.findAllBrands();
  }

  @ApiOperation(value = "Find all device template's release years")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
  })
  @GetMapping("/years")
  public List<Integer> findAllReleaseYears() {
    return deviceTemplateService.findAllReleaseYears();
  }

  @ApiOperation(value = "Update device template")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
          @ApiResponse(code = 404, message = HttpStatuses.NOT_FOUND)
  })
  @PutMapping
  public ResponseEntity<DeviceTemplateResponseDTO> update(@RequestBody DeviceTemplateRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK)
            .body(responseMapper.toDto(deviceTemplateService.update(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Create new device template")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED),
          @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
  })
  @PostMapping
  public ResponseEntity<DeviceTemplateResponseDTO> save(@RequestBody DeviceTemplateRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(responseMapper.toDto(deviceTemplateService.save(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Delete device template by id")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
          @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN),
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Long> delete(@PathVariable(name = "id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.delete(id));
  }

  @ApiOperation(value = "Find device templates using filter request")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 403, message = HttpStatuses.FORBIDDEN)
  })
  @GetMapping("/filter/page={page}")
  public ResponseEntity<DataResponse<DeviceTemplateResponseDTO>> findAllByFilter(@PathVariable(name = "page") Integer page,
                                                                                 DeviceTemplateFilterDTO request) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceTemplateService.findAllByFilter(page, request));
  }

}
