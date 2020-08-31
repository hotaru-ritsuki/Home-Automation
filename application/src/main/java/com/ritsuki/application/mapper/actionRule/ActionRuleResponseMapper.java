package com.ritsuki.application.mapper.actionRule;

import com.ritsuki.application.dto.actionRule.ActionRuleResponseDTO;
import com.ritsuki.application.entity.ActionRule;
import com.ritsuki.application.mapper.Mapper;
import com.ritsuki.application.mapper.action.ActionMapper;
import com.ritsuki.application.service.ActionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ActionRuleResponseMapper implements Mapper<ActionRule, ActionRuleResponseDTO> {
  private ModelMapper modelMapper;
  private ActionService actionService;
  private ActionMapper actionMapper;

  @Override
  public ActionRule toEntity(ActionRuleResponseDTO dto) {
    return modelMapper.map(dto, ActionRule.class);
  }

  @Override
  public ActionRuleResponseDTO toDto(ActionRule entity) {
    ActionRuleResponseDTO dto = modelMapper.map(entity, ActionRuleResponseDTO.class);
    dto.setActionSpecification(entity.getActionSpecification());
    dto.setAction(actionMapper.toDto(actionService.findAction(entity.getActionRuleId().getActionId())));
    return dto;
  }
}
