package com.jobs.board.listings.errors.handler;

import com.jobs.board.listings.errors.ErrorResponse;
import com.jobs.board.listings.response.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(ErrorResponse.class)
  public ResponseEntity<Response<String>> error(ErrorResponse errorResponse) {
    return new ResponseEntity<>(
      new Response<>(errorResponse.getCode(), errorResponse.getMessage()),
      HttpStatus.valueOf(errorResponse.getCode())
    );
  }
}
