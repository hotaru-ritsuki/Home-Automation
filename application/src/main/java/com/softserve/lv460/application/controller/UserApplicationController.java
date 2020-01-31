package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.constant.HttpStatuses;
import com.softserve.lv460.application.constant.MailMessages;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.events.OnRegistrationCompleteEvent;
import com.softserve.lv460.application.events.ResendTokenEvent;
import com.softserve.lv460.application.exception.exceptions.BadEmailOrPasswordException;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import com.softserve.lv460.application.mapper.user.JWTUserRequestMapper;
import com.softserve.lv460.application.security.annotation.CurrentUser;
import com.softserve.lv460.application.security.dto.*;
import com.softserve.lv460.application.security.entity.UserPrincipal;
import com.softserve.lv460.application.service.ApplicationUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserApplicationController {
    private final ApplicationUserService applicationUserService;
    private final AuthenticationManager authenticationManager;
    private final EmailServiceImpl mailSender;
    private final ApplicationEventPublisher eventPublisher;
    private final JWTUserRequestMapper modelMapper;


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
    public ResponseEntity<?> signUp(@RequestBody UserRegistrationRequest userRequest, final HttpServletRequest request) {
        ApplicationUser applicationUser = applicationUserService.save(userRequest);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(applicationUser, getAppUrl(request)));
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

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody @Valid UpdPasswordRequest password, @CurrentUser UserPrincipal userPrincipal) {
        userPrincipal.toString();
        if (applicationUserService.checkIfValidOldPassword(userPrincipal.getPassword(), password.getCurrentPassword())) {
            applicationUserService.changeUserPassword(userPrincipal.getId(), password.getPassword());
            mailSender.sendMessage(userPrincipal.getUsername(), MailMessages.PASWORD_CHANGED_SUBJECT,
                    String.format(MailMessages.CONGRATS, applicationUserService.findById(userPrincipal.getId()).getFirstName()) + MailMessages.PASSWORD_CHANGED_BODY);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new BadEmailOrPasswordException("Wrong password");
        }
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public ResponseEntity<?> confirmRegistration(final HttpServletRequest request, @RequestParam("token") final String token) {
        applicationUserService.validateVerificationToken(token);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/resendRegistrationToken", method = RequestMethod.GET)
    public ResponseEntity<?> resendRegistrationToken(final HttpServletRequest request, @RequestParam("email") final String email) {
        VerificationToken verificationToken = applicationUserService.generateNewVerificationToken(email);
        eventPublisher.publishEvent(new ResendTokenEvent(verificationToken.getUser(), getAppUrl(request), verificationToken.getToken()));
        return ResponseEntity.ok().build();
    }


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + "/users" + request.getContextPath();
    }


}