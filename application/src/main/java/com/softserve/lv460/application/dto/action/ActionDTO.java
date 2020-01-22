package com.softserve.lv460.application.dto.action;

import com.softserve.lv460.application.entity.enums.ActionType;
import lombok.Data;

@Data
public class ActionDTO {
  private Long id;
  private String description;
  private ActionType type;
}
