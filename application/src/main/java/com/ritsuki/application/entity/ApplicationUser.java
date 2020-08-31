package com.ritsuki.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ritsuki.application.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
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

  @ManyToMany(mappedBy = "applicationUsers",
          cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<Home> homes = new ArrayList<>();

  @Transient
  @ElementCollection(fetch = FetchType.EAGER)
  Set<Role> roles = Collections.singleton(Role.ROLE_USER);

  @NotNull
  private String secret;

  @Column(columnDefinition = "boolean default false")
  private boolean enabled;

  @NonNull
  @OneToOne(targetEntity = TelegramUser.class, fetch = FetchType.EAGER)
  @JoinColumn(referencedColumnName = "id")
  private TelegramUser telegramUser;
}



