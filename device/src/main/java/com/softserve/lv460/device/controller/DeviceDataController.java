package com.softserve.lv460.device.controller;


import com.softserve.lv460.device.constant.HttpStatuses;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.dto.deviceDto.DeviceDataDto;
import com.softserve.lv460.device.dto.parametersDto.StatisticParameters;
import com.softserve.lv460.device.service.impl.DeviceDataServiceImpl;
import com.softserve.lv460.device.service.impl.DeviceDataStatisticServiceImpl;
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
          @ApiResponse(code = 204, message = HttpStatuses.NO_CONTENT)
  })
  @PostMapping
  public ResponseEntity<Object> save(@Valid @RequestBody DeviceData deviceData) throws ExecutionException {
    deviceDataService.save(deviceData);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
