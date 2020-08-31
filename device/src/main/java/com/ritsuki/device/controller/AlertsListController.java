package com.ritsuki.device.controller;

import com.ritsuki.device.constant.HttpStatuses;
import com.ritsuki.device.document.AlertsList;
import com.ritsuki.device.dto.AlertList.AlertListDto;
import com.ritsuki.device.service.impl.AlertListServiceImpl;
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
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/alerts_list")
public class AlertsListController {
  private AlertListServiceImpl alertListService;

  @GetMapping("/homes/{home_id}")
  public List<AlertsList> getAllAlertsByHome(@PathVariable("home_id") Long homeId){
    return alertListService.findAllByHomeId(homeId);
  }

  @ApiOperation(value = "returns list of alerts")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = HttpStatuses.OK),
      @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST),
  })
  @GetMapping("/{uu_id}")
  public ResponseEntity<List<AlertListDto>> getAlerts(@PathVariable("uu_id") String uuId){
    return ResponseEntity.status(HttpStatus.OK).body(alertListService.findByUuId(uuId));
  }

  @GetMapping
  public List<AlertListDto> findAll(){
    List<AlertsList> alerts = alertListService.findAll();
    return alerts.stream().map(alert ->
        new AlertListDto(alert.getData(), alert.getId(), alert.getUuId(), alert.getTimestamp(),
                alert.getDescription(), alert.getHomeId()))
        .collect(Collectors.toList());
  }
}
