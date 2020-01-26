package com.softserve.lv460.device.exception.handler;


import com.softserve.lv460.device.constant.ExceptionMassages;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
            .errorAttributes.getErrorAttributes(request, true);
    ExceptionResponse exceptionResponse = new ExceptionResponse(errorAttributes);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
  }


  @ExceptionHandler(ConnectException.class)
  public final ResponseEntity<Object> handleConnectException(WebRequest request, ConnectException ex) {
    log.error(ex.getLocalizedMessage());
    Map<String, Object> errorAttributes = this
            .errorAttributes.getErrorAttributes(request, true);
    ExceptionResponse exceptionResponse = new ExceptionResponse(errorAttributes);
    exceptionResponse.setMessage(ExceptionMassages.CONNECTION_TO_APPLICATION_SERVICE_REFUSED);
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(exceptionResponse);
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex,
          HttpHeaders headers,
          HttpStatus status,
          WebRequest request) {
    log.error(ex.getLocalizedMessage());
    List<ValidationResponse> res =
            ex.getBindingResult().getFieldErrors().stream()
                    .map(ValidationResponse::new)
                    .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

}


