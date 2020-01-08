package com.softserve.lv460.device.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@AllArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  private ErrorAttributes errorAttributes;


  @ExceptionHandler(RuntimeException.class)
  public final ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

