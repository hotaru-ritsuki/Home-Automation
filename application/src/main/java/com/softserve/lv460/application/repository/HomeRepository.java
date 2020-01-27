package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
  Optional<Home> findByAddressaLike(String addressa);
  @Query(value="select * from home h join user_home on user_home.home_id=h.id where user_home.user_id = :userId", nativeQuery = true)
  List<Home> findByUserId(@Param("userId")Long userId);
}
