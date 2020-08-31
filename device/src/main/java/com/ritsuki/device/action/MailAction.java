package com.ritsuki.device.action;

import com.ritsuki.device.mail.EmailServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@AllArgsConstructor
@Slf4j
public class MailAction implements Action {
  private final EmailServiceImpl emailService;

  @Override
  public void execute(Map<String, String> actionData) {
    emailService.sendMessage(actionData.get("email"), "Notification", actionData.get("text"));
  }

  @Override
  public String getType() {
    return "MAIL";
  }
}
