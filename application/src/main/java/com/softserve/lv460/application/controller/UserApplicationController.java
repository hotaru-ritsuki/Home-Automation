package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.*;
import com.softserve.lv460.application.dto.telegramUser.TelegramActivationDTO;
import com.softserve.lv460.application.dto.telegramUser.TelegramUsernameDTO;
import com.softserve.lv460.application.dto.user.UserChangePasswordDto;
import com.softserve.lv460.application.dto.user.UserInfo;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.exception.exceptions.BadEmailOrPasswordException;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import com.softserve.lv460.application.mapper.user.JWTUserRequestMapper;
import com.softserve.lv460.application.security.annotation.CurrentUser;
import com.softserve.lv460.application.security.dto.*;
import com.softserve.lv460.application.security.entity.UserPrincipal;
import com.softserve.lv460.application.service.ApplicationUserService;
import com.softserve.lv460.application.service.TelegramActivationService;
import com.softserve.lv460.application.service.TelegramUserService;
import com.softserve.lv460.application.service.VerificationTokenService;
import com.softserve.lv460.application.tool.bot.HomeAlertBotService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserApplicationController {
  private final ApplicationUserService applicationUserService;
  private final AuthenticationManager authenticationManager;
  private final EmailServiceImpl mailSender;
  private final ApplicationEventPublisher eventPublisher;
  private final JWTUserRequestMapper modelMapper;
  private final LinkConfigProperties linkConfigProperties;
  private final VerificationTokenService tokenService;
  private final TelegramUserService telegramUserService;
  private final HomeAlertBotService homeAlertBotService;
  private final TelegramActivationService activationService;
  private  EmailServiceImpl service;

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
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = JWTUserRequest.class),
          @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @PostMapping("/register")
  public ResponseEntity<JWTUserRequest> signUp(@RequestBody UserRegistrationRequest userRequest) {
    ApplicationUser applicationUser = applicationUserService.save(userRequest);
    tokenService.confirmRegistration(applicationUser, getViewUrl());
    return ResponseEntity.ok().body(modelMapper.toDto(applicationUser));
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
  public ResponseEntity changePassword(@RequestBody @Valid UpdPasswordRequest password, @CurrentUser UserPrincipal userPrincipal) {
    if (applicationUserService.checkIfValidOldPassword(userPrincipal.getPassword(), password.getCurrentPassword())) {
      applicationUserService.changeUserPassword(userPrincipal.getId(), password.getPassword());
      mailSender.sendMessage(userPrincipal.getUsername(), MailMessages.PASWORD_CHANGED_SUBJECT,
              String.format(MailMessages.CONGRATS, applicationUserService.findById(userPrincipal.getId()).getFirstName()) + MailMessages.PASSWORD_CHANGED_BODY);
      return ResponseEntity.status(HttpStatus.OK).build();
    } else {
      throw new BadEmailOrPasswordException("Wrong password");
    }
  }

  @ApiOperation("Restore password")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping("/restorePassword")
  public ResponseEntity<Void> restorePassword(@RequestBody UserChangePasswordDto password) {
    System.out.println(password);
    ApplicationUser user = applicationUserService.findById(password.getId());
    applicationUserService.changeUserPassword(user.getId(), password.getPassword());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @ApiOperation("Link User to Telegram")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = HttpStatuses.OK),
          @ApiResponse(code = 400, message = HttpStatuses.BAD_REQUEST)
  })
  @PostMapping(value = "/addTelegram")
  public ResponseEntity<?> addTelegram(@CurrentUser UserPrincipal userPrincipal, @RequestBody TelegramUsernameDTO usernameDTO) {
    ApplicationUser applicationUser = applicationUserService.findById(userPrincipal.getId());
    TelegramUser telegramUser = telegramUserService.findByUsername(usernameDTO.getUsername());
    applicationUser.setTelegramUser(telegramUser);
    if (activationService.existsByTelegramUserId(telegramUser.getId())) {
      activationService.deleteByTelegramUserId(telegramUser.getId());
    }
    String token = activationService.save(telegramUser);
    applicationUserService.save(applicationUser);
    homeAlertBotService.execute(telegramUser.getChatId(), String.format(BotPhrases.CONFIRM, userPrincipal.getUsername()));
    homeAlertBotService.execute(telegramUser.getChatId(), BotPhrases.MESSAGE_EXAMPLE);
    return ResponseEntity.ok().body(new TelegramActivationDTO(token));
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
  public ResponseEntity<JWTUserRequest> sentTokenForRestorePassword(@Valid @PathVariable("email") String email) {
    ApplicationUser user = applicationUserService.findByEmail(email);
    String token = UUID.randomUUID().toString();
    tokenService.createVerificationTokenForUser(user, token);

    String confirmationUrl = getViewUrl() + "restorePassword/" + user.getId() + "/" + token;

    service.sendMessage(user.getEmail(), MailMessages.VERIFY_EMAIL_SUBJECT,String.format(MailMessages.CONGRATS, user.getFirstName())
            + String.format(MailMessages.RESTORE_EMAIL_BODY, confirmationUrl)
            + MailMessages.SIGN);

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
    if (Objects.isNull(telegramUser) || !telegramUser.isEnabled() ) {
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
  public ResponseEntity<UserInfo> getInfo(@CurrentUser UserPrincipal userPrincipal) {
    ApplicationUser user = applicationUserService.findById(userPrincipal.getId());
    return ResponseEntity.ok().body(new UserInfo(user.getFirstName(), user.getLastName()));
  }

  @ApiOperation("Set User Informations")
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = HttpStatuses.CREATED, response = JWTUserRequest.class),
          @ApiResponse(code = 400, message = ErrorMessage.USER_ALREADY_EXISTS)
  })
  @PostMapping("/setInfo")
  public ResponseEntity<UserInfo> setInfo(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserInfo userInfo) {
    ApplicationUser user = applicationUserService.findById(userPrincipal.getId());
    user.setFirstName(userInfo.getFirstName());
    user.setLastName(userInfo.getLastName());
    applicationUserService.save(user);
    return ResponseEntity.ok().body(userInfo);
  }

  public String getViewUrl() {
    return linkConfigProperties.getViewUrl() + "users/";
  }
}
