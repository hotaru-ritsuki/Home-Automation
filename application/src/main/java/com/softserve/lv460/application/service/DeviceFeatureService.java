package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.DeviceFeature;
import com.softserve.lv460.application.entity.id.DeviceFeatureId;

import java.util.List;

public interface DeviceFeatureService {

    List<DeviceFeature> findByDeviceId(Long deviceId);

    DeviceFeature findByDeviceFeatureId(DeviceFeatureId id);

    DeviceFeature update(DeviceFeature deviceFeature);

    DeviceFeature save(DeviceFeature deviceFeature);

    void delete(DeviceFeatureId id);
}
