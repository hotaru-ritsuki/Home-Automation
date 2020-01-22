package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.constant.ExceptionMassages;
import com.softserve.lv460.device.document.DeviceActionData;
import com.softserve.lv460.device.dto.enums.Status;
import com.softserve.lv460.device.exception.exceptions.ActionNotFoundException;
import com.softserve.lv460.device.repositiry.DeviceActionRepository;
import com.softserve.lv460.device.service.DeviceActionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeviceActionServiceImpl implements DeviceActionService {
  private DeviceActionRepository deviceActionRepository;

  @Override
  public DeviceActionData findByUuId(String uuId) {
    DeviceActionData deviceActionData = deviceActionRepository.findByUuIdAndStatus(uuId, Status.WAITING)
            .orElseThrow(() -> {
              throw new ActionNotFoundException
                      (String.format(ExceptionMassages.WAITING_ACTION_NOT_FOUND_BY_UUID, uuId));
            });
    deviceActionData.setStatus(Status.RECEIVED);
    return deviceActionRepository.save(deviceActionData);
  }

}
