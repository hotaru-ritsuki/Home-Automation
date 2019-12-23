package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when user trying to sign-up with email that already registered.
 *
 * @author Vasyl Petrashchuk
 */
public class UserAlreadyRegisteredException extends RuntimeException {

  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
