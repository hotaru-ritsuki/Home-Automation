package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.SupportedDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportedDeviceRepository extends JpaRepository<SupportedDevice, Long>,
        JpaSpecificationExecutor<SupportedDevice> {
}
