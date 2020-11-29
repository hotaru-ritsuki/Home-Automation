package com.ritsuki.device.service.mail.service;

public interface EmailService {

    void sendMessage(String to, String subject, String text);
}
