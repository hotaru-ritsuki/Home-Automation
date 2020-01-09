package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when user by this email not found.
 *
 * @author Vasyl Petrashchuk
 * @version 1.0
 */
public class BadEmailException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message giving message of current exception {@link BadEmailException}
   */
  public BadEmailException(String message) {
    super(message);
  }
}