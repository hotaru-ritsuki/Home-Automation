package com.softserve.lv460.application.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softserve.lv460.application.entity.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class UserPrincipal implements UserDetails {
  private Long id;

  private String username;

  @JsonIgnore
  private String password;

  private boolean enabled;

  private Collection<? extends GrantedAuthority> authorities;

  public static UserPrincipal create(ApplicationUser user) {
    List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
            new SimpleGrantedAuthority(role.name())
    ).collect(Collectors.toList());

    return new UserPrincipal(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.isEnabled(),
            authorities
    );
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}