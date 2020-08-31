package com.ritsuki.application.service;

import com.ritsuki.application.entity.DeviceFeature;
import com.ritsuki.application.entity.id.DeviceFeatureId;

import java.util.List;

public interface DeviceFeatureService {

    List<DeviceFeature> findByDeviceId(Long deviceId);

    DeviceFeature findByDeviceFeatureId(DeviceFeatureId id);

    DeviceFeature update(DeviceFeature deviceFeature);

    DeviceFeature save(DeviceFeature deviceFeature);

    void delete(DeviceFeatureId id);
}
