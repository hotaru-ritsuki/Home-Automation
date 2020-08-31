package com.ritsuki.application.exception.exceptions;

import org.springframework.security.core.AuthenticationException;

public class BadEmailOrPasswordException extends AuthenticationException {

  public BadEmailOrPasswordException(String message) {
    super(message);
  }
}
