package com.softserve.lv460.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class extends exception {@link RuntimeException}.
 *
 * @author Vasyl Petrashchuk
 * @version 1.0
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException extends RuntimeException {
    /**
     * Constructor for AppException.
     *
     * @param message - giving message of current exception {@link AppException}
     */
    public AppException(String message) {
      super(message);
    }
  }

