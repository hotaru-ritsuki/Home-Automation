package com.ritsuki.application.exception.exceptions;

public class TelegramUserAlreadyRegisterException extends RuntimeException {
  public TelegramUserAlreadyRegisterException(String message) {
    super(message);
  }
}
