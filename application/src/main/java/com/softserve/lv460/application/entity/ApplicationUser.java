package com.softserve.lv460.application.entity;

import com.softserve.lv460.application.entity.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class ApplicationUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(unique = true, nullable = false)
  private String email;

  @NotNull
  private String password;

  @NotNull
  @Column(name = "first_name")
  private String firstName;

  @NotNull
  @Column(name = "last_name")
  private String lastName;

  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinTable(
          name = "user_home",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "home_id")
  )
  private List<Home> homes = new ArrayList<>();

  @Transient
  @ElementCollection(fetch = FetchType.EAGER)
  Set<Role> roles = Collections.singleton(Role.ROLE_USER);

  @NotNull
  private String secret;

  @Column(columnDefinition = "boolean default false")
  private boolean enabled;
}



