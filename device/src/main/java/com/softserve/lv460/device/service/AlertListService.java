package com.softserve.lv460.device.service;

import com.softserve.lv460.device.dto.AlertList.AlertListDto;

import java.util.List;

public interface AlertListService {
  List<AlertListDto> findByUuid(String uuId);
  List<AlertListDto> findAll();

}
