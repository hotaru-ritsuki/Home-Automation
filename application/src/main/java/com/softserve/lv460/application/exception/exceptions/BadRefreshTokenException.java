package com.softserve.lv460.application.exception.exceptions;

public class BadRefreshTokenException extends RuntimeException {

  public BadRefreshTokenException(String message) {
    super(message);
  }
}
