package com.ritsuki.application.exception.exceptions;

public class NotCurrentUserException extends RuntimeException {

  public NotCurrentUserException(String message) {
    super(message);
  }
}
