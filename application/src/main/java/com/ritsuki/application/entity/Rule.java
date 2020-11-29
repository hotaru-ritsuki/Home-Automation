package com.ritsuki.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rules")
public class Rule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(length = 2000)
  private String conditions;

  private Boolean active;

  private String description;

  @ManyToMany(mappedBy = "rule")
  @JsonIgnore
  private List<Action> action;

}
