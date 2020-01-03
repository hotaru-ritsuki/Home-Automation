package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
  Optional<ApplicationUser> findByEmail(String email);

  @Transactional
  void deleteByEmail(String email);

  boolean existsByEmail(String email);

  Optional<ApplicationUser> findById(Long id);
}
