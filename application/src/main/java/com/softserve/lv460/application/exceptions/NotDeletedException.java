package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when we try deleting some object but such object not exist,
 * or not deleted successfully
 *
 * @version 1.0
 */
public class NotDeletedException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message - giving message of current exception {@link NotDeletedException}
   */
  public NotDeletedException(String message) {
    super(message);
  }
}