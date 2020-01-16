package com.softserve.lv460.device.repositiry;

import com.mongodb.Block;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.softserve.lv460.device.document.DeviceData;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DeviceDataStatisticRepository extends MongoRepository<DeviceData, Long> {

  Block<ChangeStreamDocument<DeviceData>> printBlock = new Block<ChangeStreamDocument<DeviceData>>() {
    @Override
    public void apply(final ChangeStreamDocument<DeviceData> deviceDataChangeStreamDocument) {
      System.out.println(deviceDataChangeStreamDocument);
    }
  };


  @Query("{'data.?0':{$exists: true},'timestamp': {$gte:?1,$lt:?2},'data.locationId': '?3'}")
  List<DeviceData> getStatistic(String type, DateTime from, DateTime to,  Long locationId);
}
