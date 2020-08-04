package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.user.UsernameDTO;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.entity.HomeToken;
import org.springframework.scheduling.annotation.Scheduled;

public interface HomeTokenService {

    void sendInvitationLink(Home home, UsernameDTO usernameTo, String usernameFrom, String appUrl);

    void delete(Long id);

    HomeToken createHomeTokenForUser(Home home, ApplicationUser user, String token);

    void validateHomeToken(String username, String token);

    @Scheduled(fixedRate = 604_800_000)
    void deleteExpired();

}
