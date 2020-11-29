package com.ritsuki.device.service;

import com.ritsuki.device.document.AlertsList;
import com.ritsuki.device.dto.AlertList.AlertListDTO;

import java.util.List;

public interface AlertListService {

    List<AlertListDTO> findByUuId(String uuId);

    List<AlertsList> findAll();

    List<AlertsList> findAllByHomeId(Long homeId);

}
