package com.ritsuki.application.exception.exceptions;

public class TelegramUserNotFound extends RuntimeException {
  public TelegramUserNotFound(String message) {
    super(message);
  }
}
