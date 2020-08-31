package com.ritsuki.application.mapper.rule;

import com.ritsuki.application.entity.Rule;
import com.ritsuki.application.service.ActionRuleService;
import com.ritsuki.application.dto.rule.RuleResponseDTO;
import com.ritsuki.application.mapper.Mapper;
import com.ritsuki.application.mapper.actionRule.ActionRuleResponseMapper;
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
    dto.setActionRule(actionRuleService.findAllByRuleId(entity.getId()).stream().map(responseMapper::toDto).collect(Collectors.toList()));
    return dto;
  }
}
