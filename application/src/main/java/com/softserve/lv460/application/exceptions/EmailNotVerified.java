package com.softserve.lv460.application.exceptions;

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
