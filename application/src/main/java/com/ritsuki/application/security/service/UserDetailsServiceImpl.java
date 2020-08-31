package com.ritsuki.application.security.service;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.security.entity.UserPrincipal;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final ApplicationUserRepository applicationUserRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email)
          throws UsernameNotFoundException {
    ApplicationUser user = applicationUserRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_BY_EMAIL, email))
    );
    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserById(Long id) {
    ApplicationUser user = applicationUserRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_BY_ID, id))
    );
    return UserPrincipal.create(user);
  }
}