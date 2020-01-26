package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
  Optional<ApplicationUser> findByEmail(String email);

  Optional<ApplicationUser> findById(Long id);

  @Transactional
  void deleteByEmail(String email);

  boolean existsByEmail(String email);

  @Modifying
  @Query("update ApplicationUser u set u.secret= ?1 where u.id = ?2")
  void updateSecret(String secret, Long id);
}
