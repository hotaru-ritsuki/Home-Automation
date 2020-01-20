package com.softserve.lv460.application.exceptions;

public class PasswordsDoNotMatchesException extends RuntimeException {

  public PasswordsDoNotMatchesException(String message) {
    super(message);
  }
}
