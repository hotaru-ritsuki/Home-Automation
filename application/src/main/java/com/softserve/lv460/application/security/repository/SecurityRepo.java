package com.softserve.lv460.application.security.repository;

import com.softserve.lv460.application.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepo extends JpaRepository<ApplicationUser, Long> {
}
