package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.DeviceFeature;
import com.softserve.lv460.application.entity.id.DeviceFeatureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceFeatureRepository extends JpaRepository<DeviceFeature, DeviceFeatureId> {
  @Query(value = "select * from device_features where device_id = :deviceId", nativeQuery = true)
  List<DeviceFeature> findByDeviceId(@Param("deviceId") Long deviceId);
}
