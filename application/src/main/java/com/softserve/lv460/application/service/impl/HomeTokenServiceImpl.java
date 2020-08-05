package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.dto.user.UsernameDTO;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.entity.HomeToken;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.exception.exceptions.TokenNotValidException;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.repository.HomeTokenRepository;
import com.softserve.lv460.application.service.HomeTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.softserve.lv460.application.constant.ErrorMessage.*;
import static com.softserve.lv460.application.constant.MailMessages.*;

@AllArgsConstructor
@Slf4j
@Service
public class HomeTokenServiceImpl implements HomeTokenService {

    private final HomeTokenRepository tokenRepository;
    private final ApplicationUserRepository userRepository;
    private final EmailServiceImpl mailSender;

    public void sendInvitationLink(Home home, UsernameDTO usernameTo, String usernameFrom, String appUrl) {
        ApplicationUser userFrom = userRepository.findByEmail(usernameFrom).get();
        String token = UUID.randomUUID().toString();
        createHomeTokenForUser(home, userFrom, token);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(usernameTo.getEmail());
        message.setSubject(INVITATION_HOME_SUBJECT);
        message.setText(INVITATION_HOME_CONGRATS
                + String.format(INVITATION_HOME_BODY, userFrom.getFirstName() + " " + userFrom.getLastName(),
                home.getName(),
                home.getCountry(),
                home.getCity(),
                home.getAddressa(),
                appUrl + "/acceptInvitationLink/" + token)
                + SIGN);
        mailSender.sendMessage(message);
    }

    public void delete(Long id) {
        if (tokenRepository.findById(id).isEmpty()) {
            throw new NotDeletedException(HOME_TOKEN_IS_EXPIRED);
        }
        tokenRepository.deleteById(id);
    }

    public HomeToken createHomeTokenForUser(Home home, ApplicationUser user, String token) {
        HomeToken myToken = new HomeToken(token, home, user);
        tokenRepository.save(myToken);
        return myToken;
    }

    public void validateHomeToken(String username, String token) {
        HomeToken homeToken = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotValidException(VERIFICATION_TOKEN_IS_NOT_VALID));
        if (homeToken.getExpiryDate().toLocalDate().isBefore(LocalDate.now()) || homeToken.getExpiryDate().toLocalDate().isEqual(LocalDate.now())) {
            tokenRepository.delete(homeToken);
            throw new TokenNotValidException(VERIFICATION_TOKEN_IS_EXPIRED);
        }
        ApplicationUser user = userRepository.findByEmail(username).get();
        List<Home> homes = user.getHomes();
        homes.add(homeToken.getHome());
        user.setHomes(homes);
        userRepository.save(user);
        tokenRepository.delete(homeToken);
    }

    public void deleteExpired() {
        int rows = tokenRepository.deleteAllByExpiryDateIsBefore(LocalDateTime.now());
        log.info(rows + " deactivared users were deleted.");
    }

}
