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
  @Query("SELECT DISTINCT dt.brand FROM DeviceTemplate dt ORDER BY dt.brand")
  List<String> findAllBrands();
  @Query("SELECT DISTINCT dt.releaseYear FROM DeviceTemplate dt ORDER BY dt.releaseYear")
  List<Integer> findAllReleaseYears();
  @Query("SELECT DISTINCT dt.type FROM DeviceTemplate dt ORDER BY dt.type")
  List<String> findAllTypes();
  @Query("SELECT DISTINCT dt.model FROM DeviceTemplate dt ORDER BY dt.model")
  List<String> findAllModels();
}
