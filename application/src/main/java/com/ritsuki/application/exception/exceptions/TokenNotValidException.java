package com.ritsuki.application.exception.exceptions;

public class TokenNotValidException extends RuntimeException {
  public TokenNotValidException(String message) {
    super(message);
  }
}
