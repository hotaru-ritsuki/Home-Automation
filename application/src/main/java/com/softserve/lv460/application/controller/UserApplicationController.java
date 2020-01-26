package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.security.dto.JWTSuccessLogIn;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.JWTUserResponse;
import com.softserve.lv460.application.security.dto.UserRegistrationRequest;
import com.softserve.lv460.application.security.jwt.JwtTokenProvider;
import com.softserve.lv460.application.service.ApplicationUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserApplicationController {
  private final ApplicationUserService applicationUserService;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  @ApiOperation("Signing-in")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK, response = JWTUserResponse.class),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping("/login")
  public ResponseEntity<JWTUserResponse> authenticateUser(@Valid @RequestBody JWTUserRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String accessToken = jwtTokenProvider.generateAccessToken(loginRequest.getEmail());
    String refreshToken = jwtTokenProvider.generateRefreshToken(loginRequest.getEmail());

    return ResponseEntity.ok(new JWTUserResponse(accessToken, refreshToken));
  }

  @ApiOperation("Registration")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED),
          @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @PostMapping("/register")
  public ResponseEntity<?> signUp(@RequestBody UserRegistrationRequest userRequest) {
    applicationUserService.save(userRequest);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @ApiOperation("Updating access token by refresh token")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = ErrorMessage.REFRESH_TOKEN_NOT_VALID)
  })

  @GetMapping("/refreshTokens")
  public ResponseEntity<Object> updateAccessToken(@RequestParam @NotBlank String refreshToken) {
    return ResponseEntity.ok().body(applicationUserService.updateAccessTokens(refreshToken));
  }


}