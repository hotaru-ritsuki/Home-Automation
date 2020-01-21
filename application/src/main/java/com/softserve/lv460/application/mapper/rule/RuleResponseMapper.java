package com.softserve.lv460.application.mapper.rule;

import com.softserve.lv460.application.dto.rule.RuleResponseDTO;
import com.softserve.lv460.application.entity.Rule;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.mapper.actionRule.ActionRuleResponseMapper;
import com.softserve.lv460.application.service.ActionRuleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class RuleResponseMapper implements Mapper<Rule, RuleResponseDTO> {
  private ModelMapper modelMapper;
  private ActionRuleService actionRuleService;
  private ActionRuleResponseMapper responseMapper;

  @Override
  public Rule toEntity(RuleResponseDTO dto) {
    return modelMapper.map(dto, Rule.class);
  }

  @Override
  public RuleResponseDTO toDto(Rule entity) {
    RuleResponseDTO dto = modelMapper.map(entity, RuleResponseDTO.class);
    dto.setUuid(entity.getLocalDevice().getUuid());
    dto.setActionRule(actionRuleService.findAllByRuleId(entity.getId()).stream()
            .map(responseMapper::toDto).collect(Collectors.toList()));
    return dto;
  }
}
