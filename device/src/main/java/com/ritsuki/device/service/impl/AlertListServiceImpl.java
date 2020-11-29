package com.ritsuki.device.service.impl;

import com.ritsuki.device.document.AlertsList;
import com.ritsuki.device.dto.AlertList.AlertListDTO;
import com.ritsuki.device.repository.AlertListRepository;
import com.ritsuki.device.service.AlertListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("alertListService")
@AllArgsConstructor
public class AlertListServiceImpl implements AlertListService {

  private final AlertListRepository alertListRepository;

  @Override
  public List<AlertListDTO> findByUuId(String uuId) {
    List<AlertsList> alertsList = alertListRepository.findByUuId(uuId);
    return alertsList.stream()
            .map(alertListRepository::save)
            .map(alert ->
        new AlertListDTO(alert.getData(), alert.getId(), alert.getUuId(), alert.getTimestamp(), alert.getDescription(), alert.getHomeId()))
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
