package com.softserve.lv460.application.exceptions;

public class BadEmailException extends RuntimeException {

  public BadEmailException(String message) {
    super(message);
  }
}