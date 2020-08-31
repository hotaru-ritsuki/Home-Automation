package com.ritsuki.application.dto.action;

import com.ritsuki.application.entity.enums.ActionType;
import lombok.Data;

@Data
public class ActionDTO {
  private Long id;
  private String description;
  private ActionType type;
}
