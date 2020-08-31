package com.ritsuki.application.repository;

import com.ritsuki.application.entity.Home;
import com.ritsuki.application.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
  List<Location> findAllByHome(Optional<Home> home);
}
