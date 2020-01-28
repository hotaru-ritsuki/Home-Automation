package com.softserve.lv460.application.exception.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
