package com.ritsuki.device.repository;

import com.ritsuki.device.document.DeviceActionData;
import com.ritsuki.device.dto.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceActionRepository extends MongoRepository<DeviceActionData, Long> {
    List<DeviceActionData> findByUuIdAndStatus(String uuId, Status status);
}
