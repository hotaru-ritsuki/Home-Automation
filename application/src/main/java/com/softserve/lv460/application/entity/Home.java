package com.softserve.lv460.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Home {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String country;
  private String city;
  private String addressa;
  private String name;
  @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
        name = "user_home",
        joinColumns = @JoinColumn(name = "home_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private List<ApplicationUser> applicationUsers;
}
