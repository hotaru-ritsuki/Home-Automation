package com.softserve.lv460.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "device_template")
public class SupportedDevice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String brand;

  @NotNull
  private String model;

  @NotNull
  private String type;

  @Column(name = "releaseYear")
  private Integer releaseYear;

  @Column(name = "powerSupply")
  private String powerSupply;

  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "supportedDevice")
  private List<LocalDevice> localDevices;

  @ManyToMany
  @JsonIgnore
  @JoinTable(name = "device_features",
          joinColumns = @JoinColumn(name = "device_id"),
          inverseJoinColumns = @JoinColumn(name = "features_id"))
  private List<Feature> features;
}

