package com.softserve.lv460.application.security.repository;

import com.softserve.lv460.application.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserRepo extends JpaRepository<ApplicationUser, Long> {
}
