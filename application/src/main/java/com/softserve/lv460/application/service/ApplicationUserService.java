package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.exception.exceptions.UserAlreadyRegisteredException;
import com.softserve.lv460.application.mapper.user.JWTUserRequestMapper;
import com.softserve.lv460.application.mapper.user.UserRegistrationRequestMapper;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.UserRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ApplicationUserService {
  private final ApplicationUserRepository applicationUserRepository;
  private final UserRegistrationRequestMapper userRegistrationRequestMapper;
  private final JWTUserRequestMapper jwtUserRequestMapper;
  private PasswordEncoder passwordEncoder;

  public JWTUserRequest save(UserRegistrationRequest userRequest) {
    if (applicationUserRepository.existsByEmail(userRequest.getEmail())) {
      throw new UserAlreadyRegisteredException("User with email already exist");
    }
    ApplicationUser applicationUser = userRegistrationRequestMapper.toEntity(userRequest);
    applicationUserRepository.save(applicationUser);
    return jwtUserRequestMapper.toDto(applicationUser);

  }

  public void changeUserPassword(ApplicationUser applicationUser, String password) {
    applicationUser.setPassword(passwordEncoder.encode(password));
    applicationUserRepository.save(applicationUser);
  }
  public boolean checkIfValidOldPassword(ApplicationUser applicationUser, String oldPassword) {
    return passwordEncoder.matches(oldPassword, applicationUser.getPassword());
  }

}
