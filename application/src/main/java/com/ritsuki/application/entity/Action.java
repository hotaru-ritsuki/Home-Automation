package com.ritsuki.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ritsuki.application.entity.enums.ActionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "actions")
public class Action {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String description;

  @NotNull
  private ActionType type;

  @ManyToMany
  @JsonIgnore
  private List<Rule> rule;
}
