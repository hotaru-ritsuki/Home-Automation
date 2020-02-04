package com.softserve.lv460.application.entity;

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
  private String conditions;
  private Boolean active;
  private String description;
  @ManyToOne
  private LocalDevice localDevice;
  @ManyToMany(mappedBy = "rule")
  @JsonIgnore
  private List<Action> action;

}