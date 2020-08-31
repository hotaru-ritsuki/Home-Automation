package com.ritsuki.device.controller;

import com.ritsuki.device.constant.HttpStatuses;
import com.ritsuki.device.dto.rule.DeviceActionDataDto;
import com.ritsuki.device.service.impl.DeviceActionServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/device-action")
public class DeviceActionController {
  private DeviceActionServiceImpl deviceActionService;


  @ApiOperation(value = "returns waiting action for device")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
  })
  @GetMapping("/{uu_id}")
  public ResponseEntity<List<DeviceActionDataDto>> getAction(@PathVariable("uu_id") String uuId) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceActionService.findByUuId(uuId));
  }
}
