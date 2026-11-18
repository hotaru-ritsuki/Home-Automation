package com.ritsuki.device.controller;

import com.ritsuki.device.constant.HttpStatuses;
import com.ritsuki.device.dto.rule.DeviceActionDataDto;
import com.ritsuki.device.service.DeviceActionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  private final DeviceActionService deviceActionService;


  @Operation(summary = "returns waiting action for device")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
          @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
  })
  @GetMapping("/{uu_id}")
  public ResponseEntity<List<DeviceActionDataDto>> getAction(@PathVariable("uu_id") String uuId) {
    return ResponseEntity.status(HttpStatus.OK).body(deviceActionService.findByUuId(uuId));
  }
}
