package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {
  List<Rule> findAllByLocalDevice(LocalDevice localDevice);

  @Query("select r from Rule r join r.localDevice d join d.location l where l in ?1")
  List<Rule> findByHome(List<Location> locations);
}
