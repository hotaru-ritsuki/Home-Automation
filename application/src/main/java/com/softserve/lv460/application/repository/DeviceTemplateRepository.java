package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.DeviceTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTemplateRepository extends JpaRepository<DeviceTemplate, Long>,
        JpaSpecificationExecutor<DeviceTemplate> {
  @Query(value = "select distinct dt.brand from device_template dt order by dt.brand", nativeQuery = true)
  List<String> findAllBrands();
  @Query(value = "select distinct dt.release_year from device_template dt order by dt.release_year", nativeQuery = true)
  List<Integer> findAllReleaseYears();
}
