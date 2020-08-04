package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.ActionRule;
import com.softserve.lv460.application.entity.id.ActionRuleId;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.repository.ActionRuleRepository;
import com.softserve.lv460.application.service.ActionRuleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ActionRuleServiceImpl implements ActionRuleService {

    private final ActionRuleRepository actionRuleRepository;

    public ActionRule create(ActionRule action) {
        if (actionRuleRepository.findById(action.getActionRuleId()).isPresent()) {
            throw new IllegalArgumentException(String.format(ErrorMessage.ACTION_RULE_ALREADY_EXISTS, action.getActionRuleId().getActionId(), action.getActionRuleId().getRuleId()));
        }
        return actionRuleRepository.save(action);
    }

    public List<ActionRule> findAll() {
        return actionRuleRepository.findAll();
    }

    public ActionRule findActionRule(ActionRule actionRule) {
        return actionRuleRepository.findById(actionRule.getActionRuleId()).orElse(actionRuleRepository.save(actionRule));
    }

    public List<ActionRule> findAllByRuleId(Long ruleId) {
        return actionRuleRepository.findByRuleId(ruleId);
    }

    public ActionRule update(ActionRule entity) {
        ActionRule action = findActionRule(entity);
        action.setActionSpecification(entity.getActionSpecification());
        return actionRuleRepository.save(action);
    }

    public void delete(ActionRuleId id) {
        if (!actionRuleRepository.findById(id).isPresent()) {
            throw new NotDeletedException(String.format(ErrorMessage.ACTION_RULE_NOT_DELETED_BY_ID, id.getActionId(), id.getRuleId()));
        }
        actionRuleRepository.deleteById(id);
    }
}
