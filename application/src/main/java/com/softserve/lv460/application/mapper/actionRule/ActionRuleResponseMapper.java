package com.softserve.lv460.application.mapper.actionRule;

import com.softserve.lv460.application.dto.actionRule.ActionRuleResponseDTO;
import com.softserve.lv460.application.entity.ActionRule;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.mapper.action.ActionMapper;
import com.softserve.lv460.application.service.ActionService;
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
