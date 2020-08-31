package com.ritsuki.application.exception.exceptions;

public class PasswordsDoNotMatchesException extends RuntimeException {

  public PasswordsDoNotMatchesException(String message) {
    super(message);
  }
}
