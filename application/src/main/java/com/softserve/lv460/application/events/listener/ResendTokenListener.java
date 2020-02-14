package com.softserve.lv460.application.events.listener;

import com.softserve.lv460.application.constant.MailMessages;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.events.ResendTokenEvent;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ResendTokenListener implements ApplicationListener<ResendTokenEvent> {
  private final EmailServiceImpl mailSender;

  @Override
  public void onApplicationEvent(ResendTokenEvent event) {
    mailSender.sendMessage(resendToken(event, event.getUser(), event.getToken()));
  }

  private SimpleMailMessage resendToken(ResendTokenEvent event, ApplicationUser user, String token) {
    String confirmationUrl = event.getAppUrl() + "confirmRegistration/" + token;
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(user.getEmail());
    email.setSubject(MailMessages.RESENDING_ACTIVATION_TOKEN_SUBJECT);
    email.setText(String.format(MailMessages.CONGRATS, user.getFirstName())
            + String.format(MailMessages.VERIFY_EMAIL_BODY, confirmationUrl)
            + MailMessages.SIGN);
    return email;
  }
}
