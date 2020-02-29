package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.entity.VerificationToken;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;
import com.softserve.lv460.application.exception.exceptions.TokenNotValidException;
import com.softserve.lv460.application.exception.exceptions.UserActivationEmailTokenExpiredException;
import com.softserve.lv460.application.exception.exceptions.UserAlreadyActivated;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import com.softserve.lv460.application.repository.ApplicationUserRepository;
import com.softserve.lv460.application.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

import static com.softserve.lv460.application.constant.ErrorMessage.*;
import static com.softserve.lv460.application.constant.MailMessages.*;

@Service
@AllArgsConstructor
public class VerificationTokenService {
  private final VerificationTokenRepository tokenRepository;
  private final ApplicationUserRepository userRepository;
  private final EmailServiceImpl mailSender;

  public void confirmRegistration(ApplicationUser applicationUser, String appUrl) {
    String token = UUID.randomUUID().toString();
    createVerificationTokenForUser(applicationUser, token);
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(applicationUser.getEmail());
    message.setSubject(VERIFY_EMAIL_SUBJECT);
    message.setText(String.format(CONGRATS, applicationUser.getFirstName())
            + String.format(VERIFY_EMAIL_BODY, appUrl + "confirmRegistration/" + token,appUrl+"resendRegistrationToken")
            + SIGN);
    mailSender.sendMessage(message);
  }

  public void resendToken(ApplicationUser user, String token, String appUrl) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(user.getEmail());
    message.setSubject(RESENDING_ACTIVATION_TOKEN_SUBJECT);
    message.setText(String.format(CONGRATS, user.getFirstName())
            + String.format(VERIFY_EMAIL_BODY, appUrl + "confirmRegistration/" + token, appUrl+"resendRegistrationToken")
            + SIGN);
    mailSender.sendMessage(message);
  }

  public void restorePassword(ApplicationUser user, String appUrl) {
    String token = UUID.randomUUID().toString();
    createVerificationTokenForUser(user, token);
    String confirmationUrl = appUrl + "restorePassword/" + user.getId() + "/" + token;
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(user.getEmail());
    email.setSubject(VERIFY_EMAIL_SUBJECT);
    email.setText(String.format(CONGRATS, user.getFirstName())
            + String.format(RESTORE_EMAIL_BODY, confirmationUrl)
            + SIGN);
    mailSender.sendMessage(email);
  }


  public VerificationToken findByUserIdAndToken(Long id, String token) {
    return tokenRepository.findByUserIdAndToken(id, token)
            .orElseThrow(() -> new UserActivationEmailTokenExpiredException(VERIFICATION_TOKEN_IS_NOT_VALID));
  }

  public void delete(Long id) {
    if (tokenRepository.findById(id).isEmpty()) {
      throw new NotDeletedException(VERIFICATION_TOKEN_IS_EXPIRED);
    }
    tokenRepository.deleteById(id);
  }

  public VerificationToken createVerificationTokenForUser(ApplicationUser user, String token) {
    VerificationToken myToken = new VerificationToken(token, user);
    tokenRepository.save(myToken);
    return myToken;
  }

  public void validateVerificationToken(String token) {
    VerificationToken verificationToken = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotValidException(VERIFICATION_TOKEN_IS_NOT_VALID));
    if (verificationToken.getExpiryDate().toLocalDate().isBefore(LocalDate.now()) || verificationToken.getExpiryDate().toLocalDate().isEqual(LocalDate.now())) {
      tokenRepository.delete(verificationToken);
      throw new TokenNotValidException(VERIFICATION_TOKEN_IS_EXPIRED);
    }
    ApplicationUser applicationUser = verificationToken.getUser();
    applicationUser.setEnabled(true);
    userRepository.save(applicationUser);
    tokenRepository.delete(verificationToken);
  }

  public VerificationToken generateNewVerificationToken(String email) throws UserAlreadyActivated {
    ApplicationUser applicationUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
    if (applicationUser.isEnabled()) {
      throw new UserAlreadyActivated(USER_ALREADY_ACTIVATED);
    }
    if (tokenRepository.findByUserId(applicationUser.getId()).isPresent()) {
      tokenRepository.delete(tokenRepository.findByUserId(applicationUser.getId()).get());
    }
    VerificationToken verificationToken = createVerificationTokenForUser(applicationUser, UUID.randomUUID().toString());
    tokenRepository.save(verificationToken);
    return verificationToken;
  }
}
