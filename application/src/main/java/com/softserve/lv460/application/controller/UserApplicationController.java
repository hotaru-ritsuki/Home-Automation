package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.exception.exceptions.BadEmailOrPasswordException;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.JWTUserResponse;
import com.softserve.lv460.application.security.dto.UpdPasswordRequest;
import com.softserve.lv460.application.security.dto.UserRegistrationRequest;
import com.softserve.lv460.application.security.jwt.JwtTokenProvider;
import com.softserve.lv460.application.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserApplicationController {
  private final ApplicationUserService applicationUserService;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<JWTUserResponse> authenticateUser(@Valid @RequestBody JWTUserRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
          )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String accessToken = jwtTokenProvider.generateAccessToken(authentication);
    String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);
    return ResponseEntity.ok(new JWTUserResponse(accessToken, refreshToken));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signUp(@RequestBody UserRegistrationRequest userRequest) {
    applicationUserService.save(userRequest);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
  public void changePassword(@Valid UpdPasswordRequest password) {

    String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    ApplicationUser applicationUser = applicationUserService.findByEmail(email);
    if (applicationUserService.checkIfValidOldPassword(applicationUser, password.getCurrentPassword())) {
      applicationUserService.changeUserPassword(applicationUser, password.getPassword());
    } else {
      throw new BadEmailOrPasswordException("Wrong password");
    }

    EmailServiceImpl mailSender = new EmailServiceImpl();
    mailSender.sendMessage(applicationUser.getEmail(), "Changed password", "Your password had been changed");

  }
}