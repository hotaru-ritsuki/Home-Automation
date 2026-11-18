package com.ritsuki.device.controller;

import com.ritsuki.device.constant.HttpStatuses;
import com.ritsuki.device.document.AlertsList;
import com.ritsuki.device.dto.AlertList.AlertListDTO;
import com.ritsuki.device.service.AlertListService;
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
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/alerts_list")
public class AlertsListController {

  private final AlertListService alertListService;

  @GetMapping("/homes/{home_id}")
  public List<AlertsList> getAllAlertsByHome(@PathVariable("home_id") Long homeId){
    return alertListService.findAllByHomeId(homeId);
  }

  @Operation(summary = "returns list of alerts")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
      @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
  })
  @GetMapping("/{uu_id}")
  public ResponseEntity<List<AlertListDTO>> getAlerts(@PathVariable("uu_id") String uuId){
    return ResponseEntity.status(HttpStatus.OK).body(alertListService.findByUuId(uuId));
  }

  @GetMapping
  public List<AlertListDTO> findAll(){
    List<AlertsList> alerts = alertListService.findAll();
    return alerts.stream().map(alert ->
        new AlertListDTO(alert.getData(), alert.getId(), alert.getUuId(), alert.getTimestamp(),
                alert.getDescription(), alert.getHomeId()))
        .collect(Collectors.toList());
  }
}
