package com.softserve.lv460.application.exception.exceptions;

public class UserActivationEmailTokenExpiredException extends RuntimeException {

  public UserActivationEmailTokenExpiredException(String message) {
    super(message);
  }
}
