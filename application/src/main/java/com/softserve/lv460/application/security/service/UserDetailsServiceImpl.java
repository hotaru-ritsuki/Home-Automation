package com.softserve.lv460.application.security.service;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.security.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final ApplicationUserRepository applicationUserRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email)
          throws UsernameNotFoundException {
    ApplicationUser user = applicationUserRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException("User not found with email : " + email)
    );
    return UserPrincipal.create(user);
  }

  // This method is used by JWTAuthenticationFilter
  @Transactional
  public UserDetails loadUserById(Long id) {
    ApplicationUser user = applicationUserRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
    );

    return UserPrincipal.create(user);
  }
}