package com.softserve.lv460.application.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class ActionRuleId implements Serializable {
  @Column(name = "rule_id")
  private Long ruleId;
  @Column(name = "action_id")
  private Long actionId;
}
