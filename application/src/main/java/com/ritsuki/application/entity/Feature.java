package com.ritsuki.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "features")
public class Feature {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @ManyToMany
  @JsonIgnore
  @JoinTable(name = "device_features",
        joinColumns = @JoinColumn(name = "features_id"),
        inverseJoinColumns = @JoinColumn(name = "device_id"))
  private List<DeviceTemplate> deviceTemplates;
}
