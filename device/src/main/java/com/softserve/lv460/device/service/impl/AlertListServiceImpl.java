package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.document.AlertsList;
import com.softserve.lv460.device.dto.AlertList.AlertListDto;
import com.softserve.lv460.device.dto.enums.Status;
import com.softserve.lv460.device.repositiry.AlertListRepository;
import com.softserve.lv460.device.service.AlertListService;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
