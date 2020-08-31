package com.ritsuki.application.repository;

import com.ritsuki.application.entity.LocalDevice;
import com.ritsuki.application.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalDeviceRepository extends JpaRepository<LocalDevice,Long> {
    Optional<LocalDevice> findByUuid(String uuid);
    List<LocalDevice> findAllByLocation(Location location);
    List<LocalDevice> findAllByLocationIn(List<Location> location);
}
