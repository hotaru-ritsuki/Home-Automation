package com.ritsuki.application.entity;

import com.ritsuki.application.entity.id.ActionRuleId;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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
