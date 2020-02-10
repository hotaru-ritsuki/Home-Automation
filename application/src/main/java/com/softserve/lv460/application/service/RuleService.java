package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.Rule;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.repository.RuleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RuleService {
  private RuleRepository ruleRepository;
  private LocationService locationService;

  public Rule create(Rule action) {
    return ruleRepository.save(action);
  }

  public List<Rule> findAll() {
    return ruleRepository.findAll();
  }

  private Rule findAction(Long id) {
    return ruleRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException(String.format(ErrorMessage.RULE_NOT_FOUND_BY_ID, id)));
  }

  public Rule update(Rule entity) {
    Rule rule = findAction(entity.getId());
    rule.setName(entity.getName());
    rule.setConditions(entity.getConditions());
    rule.setActive(entity.getActive());
    rule.setDescription(entity.getDescription());
    return ruleRepository.save(rule);
  }

  public void delete(Long id) {
    if (ruleRepository.findById(id).isEmpty()) {
      throw new NotDeletedException(String.format(ErrorMessage.RULE_NOT_DELETED_BY_ID, id));
    }
    ruleRepository.deleteById(id);
  }

  public List<Rule> findAllByLocalDevice(String uuid) {
    return ruleRepository.findAllByConditionsIsContaining(uuid);
  }

  public List<Rule> findByHome(Long homeId) {
    return ruleRepository.findAllByConditionsIsContaining("\"home_id\":\"" + homeId);
  }

}
