package com.softserve.lv460.application.exceptions;

import org.springframework.security.core.AuthenticationException;

public class BadEmailOrPasswordException extends AuthenticationException {

  public BadEmailOrPasswordException(String message) {
    super(message);
  }
}
