package com.ritsuki.application.service;

import com.ritsuki.application.entity.ActionRule;
import com.ritsuki.application.entity.id.ActionRuleId;

import java.util.List;


public interface ActionRuleService {

    ActionRule create(ActionRule action);

    List<ActionRule> findAll();

    ActionRule findActionRule(ActionRule actionRule);

    List<ActionRule> findAllByRuleId(Long ruleId);

    ActionRule update(ActionRule entity);

    void delete(ActionRuleId id);
}
