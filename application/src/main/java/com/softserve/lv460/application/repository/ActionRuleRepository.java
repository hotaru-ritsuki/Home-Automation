package com.softserve.lv460.application.repository;

import com.softserve.lv460.application.entity.ActionRule;
import com.softserve.lv460.application.entity.id.ActionRuleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRuleRepository extends JpaRepository<ActionRule, ActionRuleId> {
  @Query(value = "SELECT * from actions_rule where rule_id = :rule", nativeQuery = true)
  List<ActionRule> findByRuleId(@Param("rule") Long rule);
}
