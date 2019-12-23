package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when user trying to verify email with token that has expired.
 *
 * @author Vasyl Petrashchuk
 */
public class UserActivationEmailTokenExpiredException extends RuntimeException {
  public UserActivationEmailTokenExpiredException(String message) {
    super(message);
  }
}