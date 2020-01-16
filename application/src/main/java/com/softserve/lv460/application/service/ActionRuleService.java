package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.ActionRule;
import com.softserve.lv460.application.entity.id.ActionRuleId;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.repository.ActionRuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ActionRuleService {
  private ActionRuleRepository actionRuleRepository;

  public ActionRule create(ActionRule action) {
    if (actionRuleRepository.findById(action.getActionRuleId()).isPresent()){
      throw new IllegalArgumentException(String.format(ErrorMessage.ACTION_RULE_NOT_FOUND_BY_ID, action.getActionRuleId().getActionId(), action.getActionRuleId().getRuleId()));
    }
    return actionRuleRepository.save(action);
  }

  public List<ActionRule> findAll() {
    return actionRuleRepository.findAll();
  }

  private ActionRule findActionRule(ActionRuleId id) {
    return actionRuleRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException(String.format(ErrorMessage.ACTION_RULE_NOT_FOUND_BY_ID, id.getActionId(), id.getRuleId())));
  }

  public List<ActionRule> findAllByRuleId(Long ruleId) {
  return actionRuleRepository.findByRuleId(ruleId);
  }

  public ActionRule update(ActionRule entity) {
    ActionRule action = findActionRule(new ActionRuleId(entity.getActionRuleId().getRuleId(), entity.getActionRuleId().getActionId()));
    action.setActionSpecification(entity.getActionSpecification());
    return actionRuleRepository.save(action);
  }

  public ActionRuleId delete(ActionRuleId id) {
    if (!actionRuleRepository.findById(id).isPresent()) {
      throw new NotDeletedException(String.format(ErrorMessage.ACTION_RULE_NOT_DELETED_BY_ID, id.getActionId(), id.getRuleId()));
    }
    actionRuleRepository.deleteById(id);
    return id;
  }
}
