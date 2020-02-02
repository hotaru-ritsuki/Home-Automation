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

import static com.softserve.lv460.application.constant.ErrorMessage.USER_NOT_FOUND_BY_EMAIL;
import static com.softserve.lv460.application.constant.ErrorMessage.USER_NOT_FOUND_BY_ID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final ApplicationUserRepository applicationUserRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email)
          throws UsernameNotFoundException {
    ApplicationUser user = applicationUserRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, email))
    );
    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserById(Long id) {
    ApplicationUser user = applicationUserRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))
    );
    return UserPrincipal.create(user);
  }
}