package com.softserve.lv460.application.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Class extends Spring-Exception {@link AuthenticationException}.
 * Exception that we get when user trying to sign-in
 * to account that has not verified email.
 *
 * @version 1.0
 */
public class UserUnverifiedException extends AuthenticationException {
  /**
   * Constructor
   *
   * @param message giving message of current exception {@link UserUnverifiedException}
   */
  public UserUnverifiedException(String message) {
    super(message);
  }
}
