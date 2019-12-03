package com.jobs.board.listings.errors;

@SuppressWarnings("serial")
public class ErrorResponse extends RuntimeException {
  private Integer code;

  public ErrorResponse(String message, Integer code) {
    super(message);
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }
}
