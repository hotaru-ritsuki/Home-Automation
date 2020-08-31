package com.ritsuki.application.repository;

import com.ritsuki.application.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {
  List<Rule> findAllByConditionsIsContaining(String localDevice);
}
