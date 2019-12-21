package com.softserve.lv460.application.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "device_template")
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String brand;

  private String model;

  private String type;

  @Column(name = "releaseYear")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
  private LocalDate releaseYear;

  @Column(name = "powerSupply")
  private String powerSupply;

  @ManyToMany
  @JsonIgnore
  @JoinTable(name = "device_features",
          joinColumns = @JoinColumn(name = "device_id"),
          inverseJoinColumns = @JoinColumn(name = "features_id"))
  private List<Feature> features;
}
