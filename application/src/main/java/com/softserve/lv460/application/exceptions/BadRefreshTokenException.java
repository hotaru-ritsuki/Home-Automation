package com.softserve.lv460.application.exceptions;

public class BadRefreshTokenException extends RuntimeException {

  public BadRefreshTokenException(String message) {
    super(message);
  }
}
