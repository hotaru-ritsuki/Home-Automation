package com.ritsuki.application.exception.exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {

  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
