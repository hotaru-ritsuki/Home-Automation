package com.ritsuki.application.service;

import com.ritsuki.application.dto.telegramUser.TelegramActivationDTO;
import com.ritsuki.application.dto.user.UserInfoDTO;
import com.ritsuki.application.dto.user.UserRegistrationDTO;
import com.ritsuki.application.dto.user.UserUpdatePasswordDTO;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.security.dto.JWTSuccessLogIn;
import com.ritsuki.application.security.dto.JWTUserRequest;
import com.ritsuki.application.security.dto.JWTUserResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;

public interface ApplicationUserService {

    ApplicationUser save(UserRegistrationDTO userRequest);

    void save(ApplicationUser applicationUser);

    JWTSuccessLogIn login(JWTUserRequest loginRequest);

    ApplicationUser findByEmail(String email);

    ApplicationUser findById(Long id);

    ApplicationUser changeUserPassword(Long userId, UserUpdatePasswordDTO password);

    ApplicationUser changeUserPassword(Long userId, String newPassword);

    JWTUserResponse updateAccessTokens(String refreshToken);

    UserInfoDTO changeUserInfo(Long userId, UserInfoDTO userInfo);

    TelegramActivationDTO linkTelegramToUser(Long userId, String username);

    Boolean isDatabaseUp();

    @Scheduled(fixedRate = 604_800_000)
    void deleteExpired();

}