package com.softserve.lv460.application.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when user trying to sign-in
 * with bad email or password.
 *
 * @version 1.0
 */
public class BadEmailOrPasswordException extends AuthenticationException {
  /**
   * Constructor
   *
   * @param message giving message of current exception {@link BadEmailOrPasswordException}
   */
  public BadEmailOrPasswordException(String message) {
    super(message);
  }
}
