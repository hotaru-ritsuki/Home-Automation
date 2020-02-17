package com.softserve.lv460.application.exception.exceptions;

public class TelegramUserNotFound extends RuntimeException {
  public TelegramUserNotFound(String message) {
    super(message);
  }
}
