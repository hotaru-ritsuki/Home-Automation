package com.ritsuki.application.exception.handler;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.exception.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  private ErrorAttributes errorAttributes;

  @ExceptionHandler(RuntimeException.class)
  public final ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    log.trace(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

  @ExceptionHandler(TelegramUserNotFound.class)
  public final ResponseEntity<Object> telegramException(WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

  @ExceptionHandler(UserAlreadyRegisteredException.class)
  public final ResponseEntity<Object> userAlreadyRegisteredException(WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public final ResponseEntity<Object> handleConversionFailedException(
          MethodArgumentTypeMismatchException ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    String propName = ex.getName();
    String className = null;
    if (ex.getRequiredType() != null) {
      className = ex.getRequiredType().getSimpleName();
    }
    String message = String.format("Wrong %s. Should be '%s'", propName, className);
    exceptionResponse.setMessage(message);
    log.trace(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

  @ExceptionHandler(BadRefreshTokenException.class)
  public final ResponseEntity<Object> handleBadRefreshTokenException(WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

  @ExceptionHandler(NotCurrentUserException.class)
  public final ResponseEntity<Object> handleUserWhereNotSavedException(NotCurrentUserException ex,
                                                                       WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    log.trace(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

  @ExceptionHandler(EmailNotVerified.class)
  public final ResponseEntity<Object> handleEmailNotVerified(EmailNotVerified ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    log.trace(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

  @ExceptionHandler(AuthenticationException.class)
  public final ResponseEntity<Object> authenticationException(WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    if (!exceptionResponse.getMessage().contains("disabled")) {
      exceptionResponse.setMessage(ErrorMessage.INCORRECT_DATA);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exceptionResponse);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
          HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(getErrorAttributes(request));
    log.trace(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatus status,
          WebRequest request) {
    List<ValidationExceptionDto> collect =
            ex.getBindingResult().getFieldErrors().stream()
                    .map(ValidationExceptionDto::new)
                    .collect(Collectors.toList());
    log.trace(ex.getMessage(), ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(collect);
  }

  private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
    return new HashMap<>(errorAttributes.getErrorAttributes(webRequest, true));
  }
}