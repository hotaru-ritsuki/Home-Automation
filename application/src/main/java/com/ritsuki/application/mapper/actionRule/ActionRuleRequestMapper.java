package com.ritsuki.application.mapper.actionRule;

import com.ritsuki.application.dto.actionRule.ActionRuleRequestDTO;
import com.ritsuki.application.entity.ActionRule;
import com.ritsuki.application.entity.id.ActionRuleId;
import com.ritsuki.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ActionRuleRequestMapper implements Mapper<ActionRule, ActionRuleRequestDTO> {
  private ModelMapper modelMapper;

  @Override
  public ActionRule toEntity(ActionRuleRequestDTO dto) {
    ActionRule actionRule = new ActionRule();
    actionRule.setActionRuleId(new ActionRuleId(dto.getRuleId(), dto.getActionId()));
    actionRule.setActionSpecification(dto.getActionSpecification());
    return actionRule;
  }

  @Override
  public ActionRuleRequestDTO toDto(ActionRule entity) {
    ActionRuleRequestDTO dto = modelMapper.map(entity, ActionRuleRequestDTO.class);
    dto.setActionId(entity.getActionRuleId().getActionId());
    dto.setRuleId(entity.getActionRuleId().getRuleId());
    return dto;
  }
}
