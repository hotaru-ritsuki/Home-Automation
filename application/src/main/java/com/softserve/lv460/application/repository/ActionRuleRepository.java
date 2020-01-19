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
  @Query("SELECT a from ActionRule a where a.actionRuleId.ruleId = ?1")
  List<ActionRule> findByRuleId(@Param("rule") Long rule);
}
