package com.softserve.lv460.application.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
