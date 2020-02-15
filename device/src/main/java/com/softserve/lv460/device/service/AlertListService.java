package com.softserve.lv460.device.service;

import com.softserve.lv460.device.document.AlertsList;
import com.softserve.lv460.device.dto.AlertList.AlertListDto;

import java.util.List;
import java.util.Optional;

public interface AlertListService {
  List<AlertListDto> findByUuId(String uuId);
  List<AlertsList> findAll();

}
