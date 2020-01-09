package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when user when
 * password hasn't satisfied requirements.
 *
 * @author Vasyl Petrashchuk
 * @version 1.0
 */
public class PasswordsDoNotMatchesException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message - giving message of current exception {@link PasswordsDoNotMatchesException}
   */
  public PasswordsDoNotMatchesException(String message) {
    super(message);
  }
}
