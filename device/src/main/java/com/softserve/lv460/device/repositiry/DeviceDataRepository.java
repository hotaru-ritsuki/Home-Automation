package com.softserve.lv460.device.repositiry;

import com.softserve.lv460.device.document.DeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDataRepository extends MongoRepository<DeviceData, Long> {
}
