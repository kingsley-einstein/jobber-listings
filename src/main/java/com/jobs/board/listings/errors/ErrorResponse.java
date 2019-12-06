package com.jobs.board.listings.errors;

@SuppressWarnings("serial")
public class ErrorResponse extends RuntimeException implements java.io.Serializable {
  private Integer code;

  public ErrorResponse() {}

  public ErrorResponse(String message, Integer code) {
    super(message);
    this.code = code;
  }

  public Integer getCode() {
    return code;
  }
}
