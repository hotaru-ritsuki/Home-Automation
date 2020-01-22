package com.softserve.lv460.application.mapper.action;

import com.softserve.lv460.application.dto.action.ActionDTO;
import com.softserve.lv460.application.entity.Action;
import com.softserve.lv460.application.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ActionMapper implements Mapper<Action, ActionDTO> {
  private ModelMapper modelMapper;

  @Override
  public Action toEntity(ActionDTO dto) {
    return modelMapper.map(dto, Action.class);
  }

  @Override
  public ActionDTO toDto(Action entity) {
    return modelMapper.map(entity, ActionDTO.class);
  }
}
