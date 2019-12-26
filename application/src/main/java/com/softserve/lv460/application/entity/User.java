package com.softserve.lv460.application.entity;

import com.softserve.lv460.application.entity.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique=true,nullable=false)
  private String email;

  @NotNull
  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String password;
  @ManyToMany(fetch=FetchType.LAZY)
  @JoinTable(
          name="user_home",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "home_id")
  )
  private List<Home> homes;

  @ElementCollection(fetch = FetchType.EAGER)
  Set<Role> roles= Collections.singleton(Role.ROLE_USER);
}
