package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalDeviceRepository extends JpaRepository<LocalDevice,Long> {
    Optional<LocalDevice> findByUuid(String uuid);
    List<LocalDevice> findAllByLocation(Location location);
    @Query(value = "SELECT DISTINCT * FROM local_device JOIN location ON location.id = local_device.location_id " +
            "JOIN home ON home.id = location.home_id & :#{#home.id}  = location.home_id", nativeQuery = true)
    List<LocalDevice> findAllByHome(@Param("home") Home home);
}
