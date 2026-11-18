package com.ritsuki.device.controller;


import com.ritsuki.device.constant.HttpStatuses;
import com.ritsuki.device.document.DeviceData;
import com.ritsuki.device.dto.device.DeviceDataDTO;
import com.ritsuki.device.dto.parameters.StatisticParameters;
import com.ritsuki.device.service.DeviceDataService;
import com.ritsuki.device.service.DeviceDataStatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/device-data")
public class DeviceDataController {

  private final DeviceDataService deviceDataService;
  private final DeviceDataStatisticService deviceDataStatisticService;

  @Operation(summary = "returns last sensor indication by uuId")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
  })
  @GetMapping("/{uu_id}")
  public ResponseEntity<DeviceData> getCurrentSensorIndications(@PathVariable("uu_id") String uuId) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceDataService.getLastByUuId(uuId));
  }

  @Operation(summary = "save data from device")
  @ApiResponses({
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
          @ApiResponse(responseCode = "503", description = HttpStatuses.SERVICE_UNAVAILABLE),
          @ApiResponse(responseCode = "200", description = HttpStatuses.CREATED)
  })
  @PostMapping
  public ResponseEntity<Object> save(@Valid @RequestBody DeviceData deviceData) throws ExecutionException {
    deviceDataService.save(deviceData);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Operation(summary = "returns list of device data by specific parameters")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
  })
  @PostMapping("/statistics")
  public ResponseEntity<List<DeviceDataDTO>> getStatistic(@Valid @RequestBody StatisticParameters statisticParameters) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceDataStatisticService.getStatistic(statisticParameters));
  }


}
