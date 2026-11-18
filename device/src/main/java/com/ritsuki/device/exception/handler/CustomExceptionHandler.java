package com.ritsuki.device.exception.handler;


import com.ritsuki.device.constant.ExceptionMessages;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.webmvc.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  private ErrorAttributes errorAttributes;


  @ExceptionHandler(RuntimeException.class)
  public final ResponseEntity<Object> handleRuntimeException(WebRequest request, RuntimeException ex) {
    log.error(ex.getLocalizedMessage());
    Map<String, Object> errorAttributes = this
            .errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE, ErrorAttributeOptions.Include.MESSAGE));
    ExceptionResponse exceptionResponse = new ExceptionResponse(errorAttributes);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }


  @ExceptionHandler(ConnectException.class)
  public final ResponseEntity<Object> handleConnectException(WebRequest request, ConnectException ex) {
    log.error(ex.getLocalizedMessage());
    Map<String, Object> errorAttributes = this
            .errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE, ErrorAttributeOptions.Include.MESSAGE));
    ExceptionResponse exceptionResponse = new ExceptionResponse(errorAttributes);
    exceptionResponse.setMessage(ExceptionMessages.CONNECTION_TO_APPLICATION_SERVICE_REFUSED);
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exceptionResponse);
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatusCode status,
          WebRequest request) {
    log.error(ex.getLocalizedMessage());
    List<ValidationResponse> res =
            ex.getBindingResult().getFieldErrors().stream()
                    .map(ValidationResponse::new)
                    .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

}


