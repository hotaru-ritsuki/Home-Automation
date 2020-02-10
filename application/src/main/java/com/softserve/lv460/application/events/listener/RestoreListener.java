package com.softserve.lv460.application.events.listener;

import com.softserve.lv460.application.constant.MailMessages;
import com.softserve.lv460.application.entity.ApplicationUser;
import com.softserve.lv460.application.events.RestoreEvent;
import com.softserve.lv460.application.mail.EmailServiceImpl;
import com.softserve.lv460.application.service.ApplicationUserService;
import com.softserve.lv460.application.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class RestoreListener implements ApplicationListener<RestoreEvent>  {
    private final EmailServiceImpl mailSender;
    private final VerificationTokenService tokenService;

    @Override
    public void onApplicationEvent(RestoreEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(RestoreEvent event) {
        ApplicationUser user = event.getUser();
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationTokenForUser(user, token);
        mailSender.sendMessage(constructEmailMessage(event, user, token));
    }

    private SimpleMailMessage constructEmailMessage(RestoreEvent event, ApplicationUser user, String token) {
        String confirmationUrl = event.getAppUrl() + "restorePassword/" + user.getId() + "/" + token;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(MailMessages.VERIFY_EMAIL_SUBJECT);
        email.setText(String.format(MailMessages.CONGRATS, user.getFirstName())
                + String.format(MailMessages.RESTORE_EMAIL_BODY, confirmationUrl)
                + MailMessages.SIGN);
        return email;
    }
}
