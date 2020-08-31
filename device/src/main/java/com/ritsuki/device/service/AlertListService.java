package com.ritsuki.device.service;

import com.ritsuki.device.document.AlertsList;
import com.ritsuki.device.dto.AlertList.AlertListDto;

import java.util.List;

public interface AlertListService {
  List<AlertListDto> findByUuId(String uuId);
  List<AlertsList> findAll();
  List<AlertsList> findAllByHomeId(Long homeId);

}
