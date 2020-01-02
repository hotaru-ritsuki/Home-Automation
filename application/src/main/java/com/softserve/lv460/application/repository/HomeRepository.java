package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
  Optional<Home> findByAddressa(String address);
  Optional<Home> findByAddressaLike(String addressa);
}
