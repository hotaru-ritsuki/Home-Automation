package com.softserve.lv460.application.mapper.rule;

import com.softserve.lv460.application.dto.rule.RuleRequestDTO;
import com.softserve.lv460.application.entity.Rule;
import com.softserve.lv460.application.mapper.Mapper;
import com.softserve.lv460.application.service.LocalDeviceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RuleRequestMapper implements Mapper<Rule, RuleRequestDTO> {
  private ModelMapper modelMapper;
  private LocalDeviceService localDeviceService;

  @Override
  public Rule toEntity(RuleRequestDTO dto) {
    Rule rule = modelMapper.map(dto, Rule.class);
    rule.setLocalDevice(localDeviceService.findByUuid(dto.getUuid()));
    return rule;
  }

  @Override
  public RuleRequestDTO toDto(Rule entity) {
    RuleRequestDTO dto = modelMapper.map(entity, RuleRequestDTO.class);
    dto.setUuid(entity.getLocalDevice().getUuid());
    return dto;
  }
}
