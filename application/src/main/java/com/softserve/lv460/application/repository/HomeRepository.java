package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
<<<<<<< HEAD
  Optional<Home> findByAddressaLike(String addressa);
=======
  Optional<Home> findByAddressa(String address);
>>>>>>> 5a7092ed... dashboard added, home search via address added
}
