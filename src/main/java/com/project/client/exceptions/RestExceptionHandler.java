package com.project.client.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpResponseException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handles IOException. Thrown when no entity is found.
   *
   * @param ex IOException.
   * @return ResponseEntity - ResponseEntity object.
   */
  @ExceptionHandler(IOException.class)
  protected ResponseEntity<Object> handleIOException(IOException ex) {
    ApiResponse apiResponse = new ApiResponse(NOT_FOUND);
    apiResponse.setMessage(ex.getMessage());
    log.info(ex.getMessage());
    return buildResponseEntity(apiResponse);
  }
  /**
   * Handles HttpResponseException. Thrown when no entity is found.
   *
   * @param ex HttpResponseException.
   * @return ResponseEntity - ResponseEntity object.
   */
  @ExceptionHandler(HttpResponseException.class)
  protected ResponseEntity<Object> handleHttpResponseException(HttpResponseException ex) {
    ApiResponse apiResponse = new ApiResponse(INTERNAL_SERVER_ERROR);
    apiResponse.setMessage(ex.getMessage());
    return buildResponseEntity(apiResponse);
  }

  /**
   * Generic method to build ResponseEntity responses.
   *
   * @param apiResponse - ApiException.
   * @return ResponseEntity - ResponseEntity object.
   */
  private ResponseEntity<Object> buildResponseEntity(ApiResponse apiResponse) {
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }
}
