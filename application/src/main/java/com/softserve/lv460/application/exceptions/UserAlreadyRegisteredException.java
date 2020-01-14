package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when user trying to sign up
 * with email that already registered.
 *
 * @author Vasyl Petrashchuk
 * @version 1.0
 */
public class UserAlreadyRegisteredException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message giving message of current exception {@link UserAlreadyRegisteredException}
   */
  public UserAlreadyRegisteredException(String message) {
    super(message);
  }
}
