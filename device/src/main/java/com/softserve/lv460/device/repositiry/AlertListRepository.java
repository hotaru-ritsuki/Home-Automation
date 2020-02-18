package com.softserve.lv460.device.repositiry;

import com.softserve.lv460.device.document.AlertsList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertListRepository extends MongoRepository<AlertsList, Long> {
  List<AlertsList> findByUuId(String uuId);
  List<AlertsList> findAllByHomeId(Long homeId);
}
