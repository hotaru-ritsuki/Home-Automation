package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  @Transactional
  void deleteByEmail(String email);

  boolean existsByEmail(String email);

}
