package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when user trying to verify email
 * with token that has expired.
 *
 * @author Vasyl Petrashchuk
 * @version 1.0
 */
public class UserActivationEmailTokenExpiredException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message giving message of current exception {@link UserActivationEmailTokenExpiredException}
   */
  public UserActivationEmailTokenExpiredException(String message) {
    super(message);
  }
}
