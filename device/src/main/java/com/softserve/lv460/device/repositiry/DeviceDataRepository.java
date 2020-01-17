package com.softserve.lv460.device.repositiry;

import com.softserve.lv460.device.document.DeviceData;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceDataRepository extends MongoRepository<DeviceData, Long> {

  @Query("{'data.?0':{$exists: true},'timestamp': {$gte:?1,$lt:?2},'data.locationId': '?3'}")
  List<DeviceData> getStatistic(String type, DateTime from, DateTime to, Long locationId);

  Optional<DeviceData> findFirstByUuIdOrderByTimestampAsc(String uuId);
}
