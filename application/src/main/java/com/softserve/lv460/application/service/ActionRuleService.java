package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.ActionRule;
import com.softserve.lv460.application.entity.id.ActionRuleId;

import java.util.List;


public interface ActionRuleService {

    ActionRule create(ActionRule action);

    List<ActionRule> findAll();

    ActionRule findActionRule(ActionRule actionRule);

    List<ActionRule> findAllByRuleId(Long ruleId);

    ActionRule update(ActionRule entity);

    void delete(ActionRuleId id);
}
