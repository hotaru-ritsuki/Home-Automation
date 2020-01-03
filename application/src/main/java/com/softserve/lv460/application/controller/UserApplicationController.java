package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.JWTUserResponse;
import com.softserve.lv460.application.security.exceptions.UserAlreadyRegisteredException;
import com.softserve.lv460.application.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserApplicationController {
  @Autowired
  private ApplicationUserRepository applicationUserRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  private AuthenticationManager authenticationManager;
@Autowired
private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody JWTUserRequest loginRequest) {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginRequest.getEmail(),
                      loginRequest.getPassword()
              )
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = jwtTokenProvider.generateToken(authentication);
      return ResponseEntity.ok(new JWTUserResponse(jwt));

  }


  @PostMapping("/signup")
  public void signUp(@RequestBody JWTUserRequest userRequest) {
    if(applicationUserRepository.existsByEmail(userRequest.getEmail())) {
      throw new UserAlreadyRegisteredException("User with email already exist");
    }
    ApplicationUser user = new ApplicationUser();
    user.setEmail(userRequest.getEmail());
    user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
    applicationUserRepository.save(user);
  }
}