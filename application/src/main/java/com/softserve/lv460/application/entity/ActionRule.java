package com.softserve.lv460.application.entity;

import com.softserve.lv460.application.entity.id.ActionRuleId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "actions_rule")
public class ActionRule {
  @EmbeddedId
  private ActionRuleId actionRuleId;
  @NotNull
  @Column(name = "action_specification")
  private String actionSpecification;
}
