package com.softserve.lv460.application.events.listener;

import com.softserve.lv460.application.constant.MailMessages;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.events.OnRegistrationCompleteEvent;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import com.softserve.lv460.application.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
  private final ApplicationUserService service;
  private final EmailServiceImpl mailSender;

  @Override
  public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(final OnRegistrationCompleteEvent event) {
    ApplicationUser user = event.getUser();
    String token = UUID.randomUUID().toString();
    service.createVerificationTokenForUser(user, token);
    mailSender.sendMessage(constructEmailMessage(event, user, token));
  }

  private SimpleMailMessage constructEmailMessage(OnRegistrationCompleteEvent event, ApplicationUser user, String token) {
    String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(user.getEmail());
    email.setSubject(MailMessages.VERIFY_EMAIL_SUBJECT);
    email.setText(String.format(MailMessages.CONGRATS, user.getFirstName())
            + String.format(MailMessages.VERIFY_EMAIL_BODY, confirmationUrl)
            + MailMessages.SIGN);
    return email;
  }
}