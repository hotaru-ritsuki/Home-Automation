package com.softserve.lv460.application.entity;

import com.softserve.lv460.application.entity.id.ActionRuleId;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
//@IdClass(ActionRuleId.class)
@Table(name = "actions_rule")
public class ActionRule {
//  @Id
//  private Long rule_id;
//  @Id
//  private Long action_id;
  @EmbeddedId
  private ActionRuleId actionRuleId;
  @NotNull
  @Column(name = "action_specification")
  private String actionSpecification;
}
