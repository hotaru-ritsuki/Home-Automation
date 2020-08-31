package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.BotPhrases;
import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.dto.telegramUser.TelegramActivationDTO;
import com.ritsuki.application.dto.user.UserInfoDTO;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.entity.TelegramUser;
import com.ritsuki.application.exception.exceptions.BadEmailException;
import com.ritsuki.application.exception.exceptions.BadRefreshTokenException;
import com.ritsuki.application.exception.exceptions.PasswordsDoNotMatchesException;
import com.ritsuki.application.exception.exceptions.UserAlreadyRegisteredException;
import com.ritsuki.application.mapper.user.UserRegistrationDTOMapper;
import com.ritsuki.application.repository.ApplicationUserRepository;
import com.ritsuki.application.security.dto.JWTSuccessLogIn;
import com.ritsuki.application.security.dto.JWTUserRequest;
import com.ritsuki.application.security.dto.JWTUserResponse;
import com.ritsuki.application.security.jwt.JWTTokenProvider;
import com.ritsuki.application.service.ApplicationUserService;
import com.ritsuki.application.service.TelegramActivationService;
import com.ritsuki.application.service.TelegramUserService;
import com.ritsuki.application.tool.bot.HomeAlertBotService;
import com.ritsuki.application.dto.user.UserRegistrationDTO;
import com.ritsuki.application.dto.user.UserUpdatePasswordDTO;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final UserRegistrationDTOMapper userRegistrationDTOMapper;
    private final JWTTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final TelegramActivationService activationService;
    private final TelegramUserService telegramUserService;
    private final HomeAlertBotService homeAlertBotService;

    public ApplicationUser save(UserRegistrationDTO userRequest) {
        if (applicationUserRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserAlreadyRegisteredException(String.format(ErrorMessage.USER_ALREADY_EXISTS, userRequest.getEmail()));
        }
        ApplicationUser applicationUser = userRegistrationDTOMapper.toEntity(userRequest);
        applicationUserRepository.save(applicationUser);
        return applicationUser;
    }

    public void save(ApplicationUser applicationUser) {
        applicationUserRepository.save(applicationUser);
    }

    public JWTSuccessLogIn login(JWTUserRequest loginRequest, Authentication auth) {
        ApplicationUser user = applicationUserRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadEmailException(String.format(ErrorMessage.USER_NOT_FOUND_BY_EMAIL, loginRequest.getEmail())));
        return new JWTSuccessLogIn(user.getId(), jwtTokenProvider.generateAccessToken(auth), jwtTokenProvider.generateRefreshToken(auth), user.getFirstName());
    }

    public ApplicationUser findByEmail(String email) {
        return applicationUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_BY_EMAIL, email)));
    }

    public ApplicationUser findById(Long id) {
        return applicationUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_BY_ID, id)));
    }

    public ApplicationUser changeUserPassword(Long userId, UserUpdatePasswordDTO password) {
        ApplicationUser applicationUser = findById(userId);
        if (!passwordEncoder.matches(password.getCurrentPassword(), password.getPassword())) {
            throw new PasswordsDoNotMatchesException(ErrorMessage.PASSWORDS_DONT_MATCH);
        }
        ;
        applicationUser.setPassword(passwordEncoder.encode(password.getPassword()));
        applicationUserRepository.save(applicationUser);
        return applicationUser;
    }

    public ApplicationUser changeUserPassword(Long userId, String newPassword) {
        ApplicationUser applicationUser = findById(userId);
        applicationUser.setPassword(passwordEncoder.encode(newPassword));
        applicationUserRepository.save(applicationUser);
        return applicationUser;
    }

    @Transactional
    public JWTUserResponse updateAccessTokens(String refreshToken) {
        String email;
        try {
            email = jwtTokenProvider.getEmailFromJWT(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new BadRefreshTokenException(ErrorMessage.REFRESH_TOKEN_NOT_VALID);
        }
        ApplicationUser user = applicationUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new BadEmailException(String.format(ErrorMessage.USER_NOT_FOUND_BY_EMAIL, email)));
        applicationUserRepository.updateSecret(UUID.randomUUID().toString(), user.getId());
        if (jwtTokenProvider.isTokenValid(refreshToken, user.getSecret())) {
            return new JWTUserResponse(
                    jwtTokenProvider.generateAccessToken(email),
                    jwtTokenProvider.generateRefreshToken(email)
            );
        }
        throw new BadRefreshTokenException(ErrorMessage.REFRESH_TOKEN_NOT_VALID);
    }

    public void deleteExpired() {
        int rows = applicationUserRepository.deleteAllByEnabled(false);
        log.info(rows + " deactivated users were deleted.");
    }

    public UserInfoDTO changeUserInfo(Long userId, UserInfoDTO userInfo) {
        ApplicationUser user = findById(userId);
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        save(user);
        return userInfo;
    }

    public TelegramActivationDTO linkTelegramToUser(Long userId, String username) {
        ApplicationUser applicationUser = findById(userId);
        TelegramUser telegramUser = telegramUserService.findByUsername(username);
        if (activationService.existsByTelegramUserId(telegramUser.getId())) {
            activationService.deleteByTelegramUserId(telegramUser.getId());
        }
        applicationUser.setTelegramUser(telegramUser);
        String token = activationService.save(telegramUser);
        save(applicationUser);
        homeAlertBotService.execute(telegramUser.getChatId(), String.format(BotPhrases.CONFIRM, applicationUser.getEmail()));
        homeAlertBotService.execute(telegramUser.getChatId(), BotPhrases.MESSAGE_EXAMPLE);
        return new TelegramActivationDTO(token);
    }

    @Override
    public Boolean isDatabaseUp() {
        try {
            applicationUserRepository.selectOne();
            log.debug("Database is up");
            return true;
        } catch (Exception DBexception) {
            log.error("Database is down", DBexception);
            return false;
        }
    }
}

