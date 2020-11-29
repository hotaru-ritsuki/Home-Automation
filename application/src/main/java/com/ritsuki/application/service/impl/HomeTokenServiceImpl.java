package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.constant.MailMessages;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.entity.Home;
import com.ritsuki.application.entity.HomeToken;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.exception.exceptions.TokenNotValidException;
import com.ritsuki.application.mail.EmailServiceImpl;
import com.ritsuki.application.repository.ApplicationUserRepository;
import com.ritsuki.application.repository.HomeTokenRepository;
import com.ritsuki.application.service.HomeTokenService;
import com.ritsuki.application.dto.user.UsernameDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
        message.setSubject(MailMessages.INVITATION_HOME_SUBJECT);
        message.setText(MailMessages.INVITATION_HOME_CONGRATS
                + String.format(MailMessages.INVITATION_HOME_BODY, userFrom.getFirstName() + " " + userFrom.getLastName(),
                home.getName(),
                home.getCountry(),
                home.getCity(),
                home.getAddress(),
                appUrl + "/acceptInvitationLink/" + token)
                + MailMessages.SIGN);
        mailSender.sendMessage(message);
    }

    public void delete(Long id) {
        if (tokenRepository.findById(id).isEmpty()) {
            throw new NotDeletedException(ErrorMessage.HOME_TOKEN_IS_EXPIRED);
        }
        tokenRepository.deleteById(id);
    }

    public HomeToken createHomeTokenForUser(Home home, ApplicationUser user, String token) {
        HomeToken myToken = new HomeToken(token, home, user);
        tokenRepository.save(myToken);
        return myToken;
    }

    public void validateHomeToken(String username, String token) {
        HomeToken homeToken = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotValidException(ErrorMessage.VERIFICATION_TOKEN_IS_NOT_VALID));
        if (homeToken.getExpiryDate().toLocalDate().isBefore(LocalDate.now()) || homeToken.getExpiryDate().toLocalDate().isEqual(LocalDate.now())) {
            tokenRepository.delete(homeToken);
            throw new TokenNotValidException(ErrorMessage.VERIFICATION_TOKEN_IS_EXPIRED);
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
