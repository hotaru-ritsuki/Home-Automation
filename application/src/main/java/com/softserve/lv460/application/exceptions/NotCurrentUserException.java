package com.softserve.lv460.application.exceptions;

public class NotCurrentUserException extends RuntimeException {

  public NotCurrentUserException(String message) {
    super(message);
  }
}
