package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when we try saving some object
 * but such object already exist,
 * or saving have failed
 *
 * @author Vasyl Petrashchuk
 * @version 1.0
 */
public class NotSavedException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message - giving message of current exception {@link NotSavedException}
   */
  public NotSavedException(String message) {
    super(message);
  }
}