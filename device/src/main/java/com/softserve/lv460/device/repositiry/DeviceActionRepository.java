package com.softserve.lv460.device.repositiry;

import com.softserve.lv460.device.document.DeviceActionData;
import com.softserve.lv460.device.dto.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceActionRepository extends MongoRepository<DeviceActionData, Long> {
  Optional<DeviceActionData> findByUuIdAndStatus(String uuId, Status status);
}
