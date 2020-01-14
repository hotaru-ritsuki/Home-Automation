package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {
  List<Rule> findAllByLocalDevice(LocalDevice localDevice);
}
