package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.dto.deviceFeature.DeviceFeatureRequestDTO;
import com.softserve.lv460.application.dto.deviceFeature.DeviceFeatureResponseDTO;
import com.softserve.lv460.application.entity.id.DeviceFeatureId;
import com.softserve.lv460.application.mapper.deviceFeature.DeviceFeatureRequestMapper;
import com.softserve.lv460.application.mapper.deviceFeature.DeviceFeatureResponseMapper;
import com.softserve.lv460.application.service.DeviceFeatureService;
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
@RequestMapping("/deviceFeatures")
public class DeviceFeatureController {
  private DeviceFeatureService deviceFeatureService;
  private DeviceFeatureResponseMapper responseMapper;
  private DeviceFeatureRequestMapper requestMapper;

  @ApiOperation(value = "Update device - feature")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = DeviceFeatureResponseDTO.class)
  })
  @PutMapping
  public ResponseEntity<DeviceFeatureResponseDTO> update(DeviceFeatureRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(deviceFeatureService.update(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Find device-features with device id")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = DeviceFeatureResponseDTO.class)
  })
  @GetMapping("/{id}")
  public ResponseEntity<List<DeviceFeatureResponseDTO>> findByDeviceId(@PathVariable(name = "id") Long deviceId) {
    List<DeviceFeatureResponseDTO> responseDTO = deviceFeatureService.findByDeviceId(deviceId).stream()
            .map(e -> responseMapper.toDto(e))
            .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
  }

  @ApiOperation(value = "Create new device-feature")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = DeviceFeatureResponseDTO.class)
  })
  @PostMapping
  public ResponseEntity<DeviceFeatureResponseDTO> save(@RequestBody DeviceFeatureRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(responseMapper.toDto(deviceFeatureService.save(requestMapper.toEntity(dto))));
  }

  @ApiOperation(value = "Delete device-feature")
  @ApiResponses(value = {
          @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping
  public ResponseEntity<DeviceFeatureId> delete(@RequestBody DeviceFeatureId id) {
    deviceFeatureService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
