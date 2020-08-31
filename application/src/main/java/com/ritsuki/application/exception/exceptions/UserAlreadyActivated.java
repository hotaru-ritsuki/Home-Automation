package com.ritsuki.application.exception.exceptions;

public class UserAlreadyActivated extends RuntimeException {
  public UserAlreadyActivated(String message) {
    super(message);
  }
}
