package com.jobs.board.listings.response;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Response<T> implements java.io.Serializable {
  @JsonProperty("statusCode")
  private Integer statusCode;
  
  @JsonProperty("body")
  private T body;
  
  public Response() {}

  public Response(Integer statusCode, T body) {
    this.statusCode = statusCode;
    this.body = body;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public T getBody() {
    return body;
  }
}
