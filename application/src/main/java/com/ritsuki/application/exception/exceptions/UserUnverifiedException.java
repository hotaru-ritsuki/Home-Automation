package com.ritsuki.application.exception.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserUnverifiedException extends AuthenticationException {

  public UserUnverifiedException(String message) {
    super(message);
  }
}
