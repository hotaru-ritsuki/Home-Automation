package com.softserve.lv460.application.entity;

<<<<<<< HEAD

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Feature {

=======
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
>>>>>>> origin/feature/specification
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

<<<<<<< HEAD
  private String name;
  private String description;


=======
  @NotNull
  private String name;

  @NotNull
  private String description;

  @ManyToMany
  @JsonIgnore
  @JoinTable(name = "device_features",
          joinColumns = @JoinColumn(name = "features_id"),
          inverseJoinColumns = @JoinColumn(name = "device_id"))
  private List<SupportedDevice> supportedDevices;
>>>>>>> origin/feature/specification
}
