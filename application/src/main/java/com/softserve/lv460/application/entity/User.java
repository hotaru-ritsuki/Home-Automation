package com.softserve.lv460.application.entity;

//import com.softserve.lv460.application.entity.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "users",
    uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(unique = true, nullable=false)
  @Size(max=32)
  private String email;

  @NotNull
  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String password;


  @ManyToMany(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_home",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "home_id")
  )
  private List<Home> homes;

//  @ElementCollection(fetch = FetchType.EAGER)
//  List<Role> roles;
}