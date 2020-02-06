package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
  Optional<Home> findByAddressaLike(String addressa);

  List<Home> findAllByApplicationUsers(ApplicationUser applicationUser);
}
