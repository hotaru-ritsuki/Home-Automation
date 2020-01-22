package com.softserve.lv460.application.exceptions;

public class UserActivationEmailTokenExpiredException extends RuntimeException {

  public UserActivationEmailTokenExpiredException(String message) {
    super(message);
  }
}
