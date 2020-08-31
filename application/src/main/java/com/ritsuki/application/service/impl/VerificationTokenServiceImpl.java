package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.constant.MailMessages;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.entity.VerificationToken;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.exception.exceptions.TokenNotValidException;
import com.ritsuki.application.exception.exceptions.UserActivationEmailTokenExpiredException;
import com.ritsuki.application.exception.exceptions.UserAlreadyActivated;
import com.ritsuki.application.mail.EmailServiceImpl;
import com.ritsuki.application.repository.ApplicationUserRepository;
import com.ritsuki.application.repository.VerificationTokenRepository;
import com.ritsuki.application.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenRepository tokenRepository;
    private final ApplicationUserRepository userRepository;
    private final EmailServiceImpl mailSender;

    public void confirmRegistration(ApplicationUser applicationUser, String appUrl) {
        String token = UUID.randomUUID().toString();
        createVerificationTokenForUser(applicationUser, token);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(applicationUser.getEmail());
        message.setSubject(MailMessages.VERIFY_EMAIL_SUBJECT);
        message.setText(String.format(MailMessages.CONGRATS, applicationUser.getFirstName())
                + String.format(MailMessages.VERIFY_EMAIL_BODY, appUrl + "confirmRegistration/" + token, appUrl + "resendRegistrationToken")
                + MailMessages.SIGN);
        mailSender.sendMessage(message);
    }

    public void resendToken(ApplicationUser user, String token, String appUrl) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject(MailMessages.RESENDING_ACTIVATION_TOKEN_SUBJECT);
        message.setText(String.format(MailMessages.CONGRATS, user.getFirstName())
                + String.format(MailMessages.VERIFY_EMAIL_BODY, appUrl + "confirmRegistration/" + token, appUrl + "resendRegistrationToken")
                + MailMessages.SIGN);
        mailSender.sendMessage(message);
    }

    public void restorePassword(ApplicationUser user, String appUrl) {
        String token = UUID.randomUUID().toString();
        createVerificationTokenForUser(user, token);
        String confirmationUrl = appUrl + "restorePassword/" + user.getId() + "/" + token;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(MailMessages.VERIFY_EMAIL_SUBJECT);
        email.setText(String.format(MailMessages.CONGRATS, user.getFirstName())
                + String.format(MailMessages.RESTORE_EMAIL_BODY, confirmationUrl)
                + MailMessages.SIGN);
        mailSender.sendMessage(email);
    }

    public VerificationToken findByUserIdAndToken(Long id, String token) {
        return tokenRepository.findByUserIdAndToken(id, token)
                .orElseThrow(() -> new UserActivationEmailTokenExpiredException(ErrorMessage.VERIFICATION_TOKEN_IS_NOT_VALID));
    }

    public void delete(Long id) {
        if (tokenRepository.findById(id).isEmpty()) {
            throw new NotDeletedException(ErrorMessage.VERIFICATION_TOKEN_IS_EXPIRED);
        }
        tokenRepository.deleteById(id);
    }

    public VerificationToken createVerificationTokenForUser(ApplicationUser user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
        return myToken;
    }

    public void validateVerificationToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotValidException(ErrorMessage.VERIFICATION_TOKEN_IS_NOT_VALID));
        if (verificationToken.getExpiryDate().toLocalDate().isBefore(LocalDate.now()) || verificationToken.getExpiryDate().toLocalDate().isEqual(LocalDate.now())) {
            tokenRepository.delete(verificationToken);
            throw new TokenNotValidException(ErrorMessage.VERIFICATION_TOKEN_IS_EXPIRED);
        }
        ApplicationUser applicationUser = verificationToken.getUser();
        applicationUser.setEnabled(true);
        userRepository.save(applicationUser);
        tokenRepository.delete(verificationToken);
    }

    public VerificationToken generateNewVerificationToken(String email) throws UserAlreadyActivated {
        ApplicationUser applicationUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_BY_EMAIL, email)));
        if (applicationUser.isEnabled()) {
            throw new UserAlreadyActivated(ErrorMessage.USER_ALREADY_ACTIVATED);
        }
        if (tokenRepository.findByUserId(applicationUser.getId()).isPresent()) {
            tokenRepository.delete(tokenRepository.findByUserId(applicationUser.getId()).get());
        }
        VerificationToken verificationToken = createVerificationTokenForUser(applicationUser, UUID.randomUUID().toString());
        tokenRepository.save(verificationToken);
        return verificationToken;
    }
}
