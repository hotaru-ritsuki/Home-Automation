package com.softserve.lv460.application.exception.exceptions;

public class TokenNotValidException extends RuntimeException {
  public TokenNotValidException(String message) {
    super(message);
  }
}
