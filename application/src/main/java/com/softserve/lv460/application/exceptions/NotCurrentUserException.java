package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when token does not belong to current user
 *
 * @version 1.0
 */
public class NotCurrentUserException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message giving message of current exception {@link NotCurrentUserException}
   */
  public NotCurrentUserException(String message) {
    super(message);
  }
}
