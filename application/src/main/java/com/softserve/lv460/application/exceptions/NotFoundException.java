package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when we send request and there is no entity with such id,
 *
 * @version 1.0
 */
public class NotFoundException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message - giving message of current exception {@link NotFoundException}
   */
  public NotFoundException(String message) {
    super(message);
  }
}