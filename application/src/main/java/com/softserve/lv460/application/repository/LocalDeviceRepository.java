package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface LocalDeviceRepository extends JpaRepository<LocalDevice,Long> {
    Optional<LocalDevice> findByUuid(String uuid);
    Optional<ArrayList<LocalDevice>> findAllByLocations(Location location);
}
