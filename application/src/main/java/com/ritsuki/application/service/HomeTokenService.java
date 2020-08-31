package com.ritsuki.application.service;

import com.ritsuki.application.dto.user.UsernameDTO;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.entity.Home;
import com.ritsuki.application.entity.HomeToken;
import org.springframework.scheduling.annotation.Scheduled;

public interface HomeTokenService {

    void sendInvitationLink(Home home, UsernameDTO usernameTo, String usernameFrom, String appUrl);

    void delete(Long id);

    HomeToken createHomeTokenForUser(Home home, ApplicationUser user, String token);

    void validateHomeToken(String username, String token);

    @Scheduled(fixedRate = 604_800_000)
    void deleteExpired();

}
