package com.ritsuki.device.service.impl;

import com.ritsuki.device.document.AlertsList;
import com.ritsuki.device.dto.AlertList.AlertListDto;
import com.ritsuki.device.repository.AlertListRepository;
import com.ritsuki.device.service.AlertListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlertListServiceImpl implements AlertListService {
  private AlertListRepository alertListRepository;


  @Override
  public List<AlertListDto> findByUuId(String uuId) {
    List<AlertsList> alertsList = alertListRepository.findByUuId(uuId);
    return alertListRepository.saveAll(alertsList).stream().map(alert ->
        new AlertListDto(alert.getData(), alert.getId(), alert.getUuId(), alert.getTimestamp(),
                alert.getDescription(), alert.getHomeId()))
        .collect(Collectors.toList());
  }

  @Override
  public List<AlertsList> findAll() {
    return alertListRepository.findAll();
  }

  @Override
  public List<AlertsList> findAllByHomeId(Long homeId) {
    return alertListRepository.findAllByHomeId(homeId);
  }

}
