package com.softserve.lv460.device.exception.handler;

import lombok.Data;
import org.springframework.validation.FieldError;


@Data
class ValidationResponse {
  private String name;
  private String message;


  ValidationResponse(FieldError error) {
    this.name = error.getField();
    this.message = error.getDefaultMessage();
  }

}
