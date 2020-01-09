package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when we have bad ID.
 *
 * @author Vasyl Petrashchuk
 * @version 1.0
 */
public class BadIdException extends RuntimeException {
  /**
   * Constructor.
   *
   * @param message giving message of current exception {@link BadIdException}
   */
  public BadIdException(String message) {
    super(message);
  }
}
