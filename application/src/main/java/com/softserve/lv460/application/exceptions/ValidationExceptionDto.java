package com.softserve.lv460.application.exceptions;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.io.Serializable;

/**
 * Class extends implements {@link Serializable}
 * Custom ExceptionDto for sending messages about incorrect fiels
 *
 * @version 1.0
 */
@Data
public class ValidationExceptionDto implements Serializable {
  private String name;
  private String message;

  public ValidationExceptionDto(FieldError error) {
    this.name = error.getField();
    this.message = error.getDefaultMessage();
  }

  public ValidationExceptionDto(String name, String message) {
    this.name = name;
    this.message = message;
  }
}
