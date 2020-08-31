package com.ritsuki.application.exception.exceptions;

public class VerificationTokenNotFound extends RuntimeException {
  public VerificationTokenNotFound(String message) {
    super(message);
  }
}
