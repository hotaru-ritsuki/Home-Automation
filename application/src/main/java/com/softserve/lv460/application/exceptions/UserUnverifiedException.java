package com.softserve.lv460.application.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserUnverifiedException extends AuthenticationException {

  public UserUnverifiedException(String message) {
    super(message);
  }
}
