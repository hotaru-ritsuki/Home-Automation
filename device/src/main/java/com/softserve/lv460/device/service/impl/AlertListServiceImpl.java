package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.dto.AlertList.AlertListDto;
import com.softserve.lv460.device.repositiry.AlertListRepository;
import com.softserve.lv460.device.service.AlertListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlertListServiceImpl implements AlertListService {
  private AlertListRepository alertListRepository;


  @Override
  public List<AlertListDto> findByUuid(String uuId) {
    return null;
  }

  @Override
  public List<AlertListDto> findAll() {
    return null;
  }
}
