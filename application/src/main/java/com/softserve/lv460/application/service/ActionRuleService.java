package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.ActionRule;
import com.softserve.lv460.application.entity.id.ActionRuleId;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.repository.ActionRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ActionRuleService {
  private ActionRuleRepository actionRuleRepository;

  public ActionRule create(ActionRule action) {
    if (actionRuleRepository.findById(action.getActionRuleId()).isPresent()) {
      throw new IllegalArgumentException(String.format(ErrorMessage.ACTION_RULE_ALREADY_EXISTS, action.getActionRuleId().getActionId(), action.getActionRuleId().getRuleId()));
    }
    return actionRuleRepository.save(action);
  }

  public List<ActionRule> findAll() {
    return actionRuleRepository.findAll();
  }

  private ActionRule findActionRule(ActionRule actionRule) {
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
