package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.exception.exceptions.BadEmailOrPasswordException;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import com.softserve.lv460.application.security.dto.*;
import com.softserve.lv460.application.service.ApplicationUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserApplicationController {
  private final ApplicationUserService applicationUserService;
  private final AuthenticationManager authenticationManager;

  @ApiOperation("Signing-in")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = JWTUserResponse.class),
        @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping("/login")
  public ResponseEntity<JWTSuccessLogIn> authenticateUser(@Valid @RequestBody JWTUserRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
          )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return ResponseEntity.ok(applicationUserService.login(loginRequest, authentication));
  }

  @ApiOperation("Registration")
  @ApiResponses(value = {
        @ApiResponse(code = 201, message = HttpStatuses.CREATED),
        @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @PostMapping("/register")
  public ResponseEntity<?> signUp(@RequestBody UserRegistrationRequest userRequest) {
    return ResponseEntity.ok().body(applicationUserService.save(userRequest));
  }

  @ApiOperation("Updating access token by refresh token")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = HttpStatuses.OK, response = JWTUserResponse.class),
        @ApiResponse(code = 400, message = ErrorMessage.REFRESH_TOKEN_NOT_VALID)
  })

  @GetMapping("/refreshTokens")
  public ResponseEntity<JWTUserResponse> updateAccessToken(@RequestParam @NotBlank String refreshToken) {
    return ResponseEntity.ok().body(applicationUserService.updateAccessTokens(refreshToken));
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