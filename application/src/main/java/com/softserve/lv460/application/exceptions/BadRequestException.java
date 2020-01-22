package com.softserve.lv460.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class extends exception {@link RuntimeException}.
 * Exception that we get when user trying to pass bad request.
 *
 * @version 1.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
  /**
   * Constructor
   *
   * @param message - giving message of current exception {@link BadRequestException}
   */
  public BadRequestException(String message) {
    super(message);
  }
}
