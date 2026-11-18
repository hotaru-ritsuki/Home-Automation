package com.ritsuki.application.web.rest;

import com.ritsuki.application.constant.HttpStatuses;
import com.ritsuki.application.dto.deviceFeature.DeviceFeatureRequestDTO;
import com.ritsuki.application.dto.deviceFeature.DeviceFeatureResponseDTO;
import com.ritsuki.application.service.DeviceFeatureService;
import com.ritsuki.application.entity.id.DeviceFeatureId;
import com.ritsuki.application.mapper.deviceFeature.DeviceFeatureRequestMapper;
import com.ritsuki.application.mapper.deviceFeature.DeviceFeatureResponseMapper;
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
@RequestMapping("/deviceFeatures")
public class DeviceFeatureController {

  private final DeviceFeatureService deviceFeatureService;
  private final DeviceFeatureResponseMapper responseMapper;
  private final DeviceFeatureRequestMapper requestMapper;

  @Operation(summary = "Update device - feature")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = DeviceFeatureResponseDTO.class)))
  })
  @PutMapping
  public ResponseEntity<DeviceFeatureResponseDTO> update(DeviceFeatureRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK).body(responseMapper.toDto(deviceFeatureService.update(requestMapper.toEntity(dto))));
  }

  @Operation(summary = "Find device-features with device id")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK, content = @Content(schema = @Schema(implementation = DeviceFeatureResponseDTO.class)))
  })
  @GetMapping("/{id}")
  public ResponseEntity<List<DeviceFeatureResponseDTO>> findByDeviceId(@PathVariable(name = "id") Long deviceId) {
    List<DeviceFeatureResponseDTO> responseDTO = deviceFeatureService.findByDeviceId(deviceId).stream()
            .map(e -> responseMapper.toDto(e))
            .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
  }

  @Operation(summary = "Create new device-feature")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = HttpStatuses.CREATED, content = @Content(schema = @Schema(implementation = DeviceFeatureResponseDTO.class)))
  })
  @PostMapping
  public ResponseEntity<DeviceFeatureResponseDTO> save(@RequestBody DeviceFeatureRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(responseMapper.toDto(deviceFeatureService.save(requestMapper.toEntity(dto))));
  }

  @Operation(summary = "Delete device-feature")
  @ApiResponses({
          @ApiResponse(responseCode = "204", description = HttpStatuses.NO_CONTENT),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST)
  })
  @DeleteMapping
  public ResponseEntity<DeviceFeatureId> delete(@RequestBody DeviceFeatureId id) {
    deviceFeatureService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
