package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
  List<Location> findAllByHome(Optional<Home> home);
}
