package com.ritsuki.application.repository;

import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {

  Optional<Home> findByAddressLike(String address);

  List<Home> findAllByApplicationUsers(ApplicationUser applicationUser);

}
