package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.entity.DeviceFeature;
import com.ritsuki.application.entity.id.DeviceFeatureId;
import com.ritsuki.application.exception.exceptions.NotCreatedException;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.exception.exceptions.NotFoundException;
import com.ritsuki.application.repository.DeviceFeatureRepository;
import com.ritsuki.application.service.DeviceFeatureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class DeviceFeatureServiceImpl implements DeviceFeatureService {
    private final DeviceFeatureRepository deviceFeatureRepository;

    public List<DeviceFeature> findByDeviceId(Long deviceId) {
        return deviceFeatureRepository.findByDeviceId(deviceId);
    }

    public DeviceFeature findByDeviceFeatureId(DeviceFeatureId id) {
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
