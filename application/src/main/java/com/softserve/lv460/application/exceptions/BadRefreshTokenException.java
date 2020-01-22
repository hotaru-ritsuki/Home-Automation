package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when user trying to
 * get access token with bad or expired refresh token
 *
 * @version 1.0
 */
public class BadRefreshTokenException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message message of current exception {@link BadRefreshTokenException}
   */
  public BadRefreshTokenException(String message) {
    super(message);
  }
}
