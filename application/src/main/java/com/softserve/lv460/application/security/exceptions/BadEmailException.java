package com.softserve.lv460.application.security.exceptions;

/**
 * @author Vasyl Petrashchuk
 *
 * Exception that we get when user trying to sign-up with already registered email
 */
  public class BadEmailException extends RuntimeException {
    public BadEmailException(String message) {
      super(message);
    }
  }

