package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.exceptions.UserAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserApplicationController {
@Autowired
  private ApplicationUserRepository applicationUserRepository;
@Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

@Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
  @PostMapping("/signup")
  public void signUp(@RequestBody JWTUserRequest userRequest) {
  if(applicationUserRepository.existsByEmail(userRequest.getEmail())){
    throw new UserAlreadyRegisteredException("User with email already exist");
  }
  ApplicationUser user=new ApplicationUser();
  user.setEmail(userRequest.getEmail());
  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  applicationUserRepository.save(user);
  }
  @PostMapping("/login")
  public void logIn(@RequestBody JWTUserRequest userRequest){

  }
}