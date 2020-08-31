package com.ritsuki.application.exception.exceptions;

public class BadEmailException extends RuntimeException {

  public BadEmailException(String message) {
    super(message);
  }
}