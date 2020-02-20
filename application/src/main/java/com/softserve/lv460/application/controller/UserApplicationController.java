package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.constant.LinkConfigProperties;
import com.softserve.lv460.application.dto.telegramUser.TelegramActivationDTO;
import com.softserve.lv460.application.dto.telegramUser.TelegramUsernameDTO;
import com.softserve.lv460.application.dto.user.*;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.mapper.user.JWTUserRequestMapper;
import com.softserve.lv460.application.mapper.user.UserInfoDTOMapper;
import com.softserve.lv460.application.mapper.user.UsernameDTOMapper;
import com.softserve.lv460.application.security.annotation.CurrentUser;
import com.softserve.lv460.application.security.dto.JWTSuccessLogIn;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.JWTUserResponse;
import com.softserve.lv460.application.security.entity.UserPrincipal;
import com.softserve.lv460.application.service.ApplicationUserService;
import com.softserve.lv460.application.service.TelegramUserService;
import com.softserve.lv460.application.service.VerificationTokenService;
import com.softserve.lv460.application.tool.bot.HomeAlertBotService;
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
import java.util.Objects;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserApplicationController {
  private final ApplicationUserService applicationUserService;
  private final LinkConfigProperties linkConfigProperties;
  private final AuthenticationManager authenticationManager;
  private final VerificationTokenService tokenService;
  private final JWTUserRequestMapper modelMapper;
  private final UsernameDTOMapper usernameMapper;
  private final UserInfoDTOMapper userInfoDTOMapper;

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
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = UsernameDTO.class),
          @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @PostMapping("/register")
  public ResponseEntity<UsernameDTO> signUp(@Valid @RequestBody UserRegistrationDTO userRequest) {
    ApplicationUser applicationUser = applicationUserService.save(userRequest);
    tokenService.confirmRegistration(applicationUser, getViewUrl());
    return ResponseEntity.ok().body(usernameMapper.toDto(applicationUser));
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

  @ApiOperation("Changing Password")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping("/changePassword")
  public ResponseEntity changePassword(@RequestBody @Valid UserUpdatePasswordDTO password, @CurrentUser UserPrincipal userPrincipal) {
    return ResponseEntity.status(HttpStatus.OK).body(usernameMapper.toDto(applicationUserService.changeUserPassword(userPrincipal.getId(), password)));
  }

  @ApiOperation("Restore password")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping("/restorePassword")
  public ResponseEntity<?> restorePassword(@RequestBody UserChangePasswordDTO password) {
    return ResponseEntity.status(HttpStatus.OK).body(usernameMapper.toDto(applicationUserService.changeUserPassword(password.getId(), password.getPassword())));
  }

  @ApiOperation("Link User to Telegram")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping(value = "/addTelegram")
  public ResponseEntity<TelegramActivationDTO> addTelegram(@CurrentUser UserPrincipal userPrincipal, @RequestBody TelegramUsernameDTO usernameDTO) {
    return ResponseEntity.ok().body(applicationUserService.linkTelegramToUser(userPrincipal.getId(), usernameDTO.getUsername()));
  }

  @ApiOperation("Confirming registration")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @GetMapping("/confirmRegistration/{token}")
  public ResponseEntity confirmRegistration(@PathVariable("token") String token) {
    tokenService.validateVerificationToken(token);
    return ResponseEntity.ok().build();
  }

  @ApiOperation("Request to resend confirmation token")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @GetMapping(value = "/resendRegistrationToken/{email}")
  public ResponseEntity resendRegistrationToken(@PathVariable("email") String email) {
    VerificationToken verificationToken = tokenService.generateNewVerificationToken(email);
    ApplicationUser applicationUser = applicationUserService.findByEmail(email);
    tokenService.resendToken(applicationUser, verificationToken.getToken(), getViewUrl());
    return ResponseEntity.ok().build();
  }

  @ApiOperation("Send restore password")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = JWTUserRequest.class),
          @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @GetMapping("/restorePassword/{email}")
  public ResponseEntity<JWTUserRequest> sentTokenForRestorePassword(@PathVariable("email") String email) {
    ApplicationUser user = applicationUserService.findByEmail(email);
    tokenService.restorePassword(user, getViewUrl());
    return ResponseEntity.ok().body(modelMapper.toDto(user));
  }

  @ApiOperation("Get restore password")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = JWTUserRequest.class),
          @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @GetMapping("/restorePassword/{user_id}/{token}")
  public ResponseEntity<VerificationToken> checkValidRestoreToken(@PathVariable("user_id") long id, @PathVariable("token") String token) {
    VerificationToken verificationToken = tokenService.findByUserIdAndToken(id, token);
    tokenService.delete(verificationToken.getId());
    return ResponseEntity.ok().body(verificationToken);
  }

  @ApiOperation("Get telegram")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED),
          @ApiResponse(code = 400, message = ErrorMessage.TELEGRAM_NOT_FOUND)
  })
  @GetMapping("/getTelegramUser")
  public ResponseEntity<TelegramUser> getTelegramUser(@CurrentUser UserPrincipal userPrincipal) {
    TelegramUser telegramUser = applicationUserService.findById(userPrincipal.getId()).getTelegramUser();
    if (Objects.isNull(telegramUser) || !telegramUser.isEnabled()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok().body(telegramUser);
  }

  @ApiOperation("Get User Credentials")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = JWTUserRequest.class),
          @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @GetMapping("/getInfo")
  public ResponseEntity<UserInfoDTO> getInfo(@CurrentUser UserPrincipal userPrincipal) {
    return ResponseEntity.ok().body(userInfoDTOMapper.toDto(applicationUserService.findById(userPrincipal.getId())));
  }

  @ApiOperation("Set User's Information")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = JWTUserRequest.class),
          @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @PostMapping("/setInfo")
  public ResponseEntity<UserInfoDTO> setInfo(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserInfoDTO userInfo) {
    return ResponseEntity.ok().body(applicationUserService.changeUserInfo(userPrincipal.getId(), userInfo));
  }

  public String getViewUrl() {
    return linkConfigProperties.getViewUrl() + "users/";
  }
}
