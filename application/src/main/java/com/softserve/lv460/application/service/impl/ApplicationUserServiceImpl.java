package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.constant.BotPhrases;
import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.dto.telegramUser.TelegramActivationDTO;
import com.softserve.lv460.application.dto.user.UserInfoDTO;
import com.softserve.lv460.application.dto.user.UserRegistrationDTO;
import com.softserve.lv460.application.dto.user.UserUpdatePasswordDTO;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.TelegramUser;
import com.softserve.lv460.application.exception.exceptions.BadEmailException;
import com.softserve.lv460.application.exception.exceptions.BadRefreshTokenException;
import com.softserve.lv460.application.exception.exceptions.PasswordsDoNotMatchesException;
import com.softserve.lv460.application.exception.exceptions.UserAlreadyRegisteredException;
import com.softserve.lv460.application.mapper.user.UserRegistrationDTOMapper;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.security.dto.JWTSuccessLogIn;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.JWTUserResponse;
import com.softserve.lv460.application.security.jwt.JWTTokenProvider;
import com.softserve.lv460.application.service.ApplicationUserService;
import com.softserve.lv460.application.service.TelegramActivationService;
import com.softserve.lv460.application.service.TelegramUserService;
import com.softserve.lv460.application.tool.bot.HomeAlertBotService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.softserve.lv460.application.constant.ErrorMessage.*;

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
            throw new UserAlreadyRegisteredException(String.format(USER_ALREADY_EXISTS, userRequest.getEmail()));
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
                .orElseThrow(() -> new BadEmailException(String.format(USER_NOT_FOUND_BY_EMAIL, loginRequest.getEmail())));
        return new JWTSuccessLogIn(user.getId(), jwtTokenProvider.generateAccessToken(auth), jwtTokenProvider.generateRefreshToken(auth), user.getFirstName());
    }

    public ApplicationUser findByEmail(String email) {
        return applicationUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
    }

    public ApplicationUser findById(Long id) {
        return applicationUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));
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
            throw new BadRefreshTokenException(REFRESH_TOKEN_NOT_VALID);
        }
        ApplicationUser user = applicationUserRepository
                .findByEmail(email)
                .orElseThrow(() -> new BadEmailException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
        applicationUserRepository.updateSecret(UUID.randomUUID().toString(), user.getId());
        if (jwtTokenProvider.isTokenValid(refreshToken, user.getSecret())) {
            return new JWTUserResponse(
                    jwtTokenProvider.generateAccessToken(email),
                    jwtTokenProvider.generateRefreshToken(email)
            );
        }
        throw new BadRefreshTokenException(REFRESH_TOKEN_NOT_VALID);
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
}

