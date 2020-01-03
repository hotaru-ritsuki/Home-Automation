package com.softserve.lv460.application.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException extends RuntimeException {
    /**
     * Constructor for AppException.
     *
     * @param message - giving message.
     */
    public AppException(String message) {
      super(message);
    }
  }

