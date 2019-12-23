package com.softserve.lv460.application.entity;

import com.softserve.lv460.application.entity.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
  @ManyToMany
  private List<Home> homes;

  @ElementCollection(fetch = FetchType.EAGER)
  List<Role> roles;
}
