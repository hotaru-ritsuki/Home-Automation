package com.ritsuki.device.action;

import com.ritsuki.device.service.mail.impl.EmailServiceImpl;
import com.ritsuki.device.service.mail.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class MailAction implements Action {

    private final EmailService emailService;

    @Override
    public void execute(Map<String, String> actionData) {
        emailService.sendMessage(actionData.get("email"), "Notification", actionData.get("text"));
    }

    @Override
    public String getType() {
        return "MAIL";
    }
}
