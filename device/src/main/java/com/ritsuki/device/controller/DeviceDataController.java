package com.ritsuki.device.controller;


import com.ritsuki.device.constant.HttpStatuses;
import com.ritsuki.device.document.DeviceData;
import com.ritsuki.device.dto.device.DeviceDataDto;
import com.ritsuki.device.dto.parameters.StatisticParameters;
import com.ritsuki.device.service.impl.DeviceDataServiceImpl;
import com.ritsuki.device.service.impl.DeviceDataStatisticServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@AllArgsConstructor
@RequestMapping("/device-data")
public class DeviceDataController {
  private DeviceDataServiceImpl deviceDataService;
  private DeviceDataStatisticServiceImpl deviceDataStatisticService;

  @ApiOperation(value = "returns last sensor indication by uuId")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
  })
  @GetMapping("/{uu_id}")
  public ResponseEntity<DeviceData> getCurrentSensorIndications(@PathVariable("uu_id") String uuId) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceDataService.getLastByUuId(uuId));
  }

  @ApiOperation(value = "save data from device")
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
          @ApiResponse(code = 503, message = HttpStatuses.SERVICE_UNAVAILABLE),
          @ApiResponse(code = 200, message = HttpStatuses.CREATED)
  })
  @PostMapping
  public ResponseEntity<Object> save(@Valid @RequestBody DeviceData deviceData) throws ExecutionException {
    deviceDataService.save(deviceData);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @ApiOperation(value = "returns list of device data by specific parameters")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
  })
  @PostMapping("/statistics")
  public ResponseEntity<List<DeviceDataDto>> getStatistic(@Valid @RequestBody StatisticParameters statisticParameters) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceDataStatisticService.getStatistic(statisticParameters));
  }


}
