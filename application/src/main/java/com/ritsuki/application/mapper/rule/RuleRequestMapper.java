package com.ritsuki.application.mapper.rule;

import com.ritsuki.application.entity.Rule;
import com.ritsuki.application.dto.rule.RuleRequestDTO;
import com.ritsuki.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RuleRequestMapper implements Mapper<Rule, RuleRequestDTO> {
  private ModelMapper modelMapper;

  @Override
  public Rule toEntity(RuleRequestDTO dto) {
    return modelMapper.map(dto, Rule.class);
  }

  @Override
  public RuleRequestDTO toDto(Rule entity) {
    return modelMapper.map(entity, RuleRequestDTO.class);
  }
}
