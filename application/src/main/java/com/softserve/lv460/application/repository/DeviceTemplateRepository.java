package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.DeviceTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTemplateRepository extends JpaRepository<DeviceTemplate, Long>,
        JpaSpecificationExecutor<DeviceTemplate> {
}
