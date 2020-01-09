package com.softserve.lv460.application.exceptions;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when a use tries
 * to sign in before email verification.
 *
 * @author Vasyl Petrashchuk
 * @version 1.0
 */
public class EmailNotVerified extends RuntimeException {

  public EmailNotVerified() {
  }

  public EmailNotVerified(String message) {
    super(message);
  }


  public EmailNotVerified(String message, Throwable cause) {
    super(message, cause);
  }
}
