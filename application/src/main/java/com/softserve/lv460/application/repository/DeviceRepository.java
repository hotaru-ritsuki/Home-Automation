package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.dto.device.DeviceResponse;
import com.softserve.lv460.application.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device> {

}
