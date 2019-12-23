package com.softserve.lv460.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @ManyToOne
  private Home home;
<<<<<<< HEAD
=======

  @ManyToMany(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinTable(name = "local_device",
    joinColumns = @JoinColumn(name = "location_id"),
    inverseJoinColumns = @JoinColumn(name = "device_id"))
  private List<Feature> devices;

>>>>>>> 5a7092ed... dashboard added, home search via address added
}
