package com.softserve.lv460.application.exception.exceptions;

public class TelegramUserAlreadyRegisterException extends RuntimeException {
  public TelegramUserAlreadyRegisterException(String message) {
    super(message);
  }
}
