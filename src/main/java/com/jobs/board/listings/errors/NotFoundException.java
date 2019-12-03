package com.jobs.board.listings.errors;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
  private Integer code;

  public NotFoundException(String message, Integer code) {
    super(message);
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }
}
