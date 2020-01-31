package com.softserve.lv460.application.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl {

  private final JavaMailSender emailSender;

  public void sendMessage(String to, String subject, String text) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(to);
      message.setSubject(subject);
      message.setText(text);
      emailSender.send(message);
    } catch (MailException exception) {
      exception.printStackTrace();
    }
  }
  public void sendMessage(SimpleMailMessage simpleMailMessage){
emailSender.send(simpleMailMessage);
  }
}