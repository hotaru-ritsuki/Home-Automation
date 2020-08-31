package com.ritsuki.application.exception.exceptions;

public class BadRefreshTokenException extends RuntimeException {

  public BadRefreshTokenException(String message) {
    super(message);
  }
}
