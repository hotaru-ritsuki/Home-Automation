package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.DeviceFeature;
import com.softserve.lv460.application.entity.id.DeviceFeatureId;
import com.softserve.lv460.application.exception.exceptions.NotCreatedException;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.exception.exceptions.NotFoundException;
import com.softserve.lv460.application.repository.DeviceFeatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeviceFeatureService {
  private DeviceFeatureRepository deviceFeatureRepository;

  public List<DeviceFeature> findByDeviceId(Long deviceId) {
    return deviceFeatureRepository.findByDeviceId(deviceId);
  }

  private DeviceFeature findByDeviceFeatureId(DeviceFeatureId id) {
    return deviceFeatureRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format(ErrorMessage.DEVICE_FEATURE_NOT_FOUND_BY_ID,
                    id.getDeviceId(), id.getFeatureId())));
  }

  public DeviceFeature update(DeviceFeature deviceFeature) {
    findByDeviceFeatureId(deviceFeature.getDeviceFeatureId());
    return deviceFeatureRepository.save(deviceFeature);
  }

  public DeviceFeature save(DeviceFeature deviceFeature) {
    if (deviceFeatureRepository.findById(deviceFeature.getDeviceFeatureId()).isPresent()) {
      throw new NotCreatedException((String.format(ErrorMessage.DEVICE_FEATURE_ALREADY_EXISTS,
              deviceFeature.getDeviceFeatureId().getDeviceId(), deviceFeature.getDeviceFeatureId().getFeatureId())));
    }
    return deviceFeatureRepository.save(deviceFeature);
  }

  public void delete(DeviceFeatureId id) {
    if (!deviceFeatureRepository.findById(id).isPresent()) {
      throw new NotDeletedException(String.format(ErrorMessage.DEVICE_FEATURE_NOT_DELETED_BY_ID, id.getDeviceId(),
              id.getFeatureId()));
    }
    deviceFeatureRepository.deleteById(id);
  }
}
