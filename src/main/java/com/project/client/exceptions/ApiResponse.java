package com.project.client.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
  private HttpStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;

  private String message;
  private String debugMessage;
  private List<ApiValidationError> subErrors;

  public ApiResponse() {
    timestamp = LocalDateTime.now();
  }

  public ApiResponse(HttpStatus status) {
    this();
    this.status = status;
  }
}
