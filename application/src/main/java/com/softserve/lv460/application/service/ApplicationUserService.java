package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.telegramUser.TelegramActivationDTO;
import com.softserve.lv460.application.dto.user.UserInfoDTO;
import com.softserve.lv460.application.dto.user.UserRegistrationDTO;
import com.softserve.lv460.application.dto.user.UserUpdatePasswordDTO;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.security.dto.JWTSuccessLogIn;
import com.softserve.lv460.application.security.dto.JWTUserRequest;
import com.softserve.lv460.application.security.dto.JWTUserResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;

public interface ApplicationUserService {

    ApplicationUser save(UserRegistrationDTO userRequest);

    void save(ApplicationUser applicationUser);

    JWTSuccessLogIn login(JWTUserRequest loginRequest, Authentication auth);

    ApplicationUser findByEmail(String email);

    ApplicationUser findById(Long id);

    ApplicationUser changeUserPassword(Long userId, UserUpdatePasswordDTO password);

    ApplicationUser changeUserPassword(Long userId, String newPassword);

    JWTUserResponse updateAccessTokens(String refreshToken);

    UserInfoDTO changeUserInfo(Long userId, UserInfoDTO userInfo);

    TelegramActivationDTO linkTelegramToUser(Long userId, String username);

    @Scheduled(fixedRate = 604_800_000)
    void deleteExpired();

}