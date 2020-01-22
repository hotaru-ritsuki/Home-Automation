package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when we try updating some object
 * but such object not exist,
 * or updating have failed
 *
 * @version 1.0
 */
public class NotUpdatedException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message - giving message of current exception {@link NotUpdatedException}
   */
  public NotUpdatedException(String message) {
    super(message);
  }
}