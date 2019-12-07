package com.jobs.board.listings.errors.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobs.board.listings.errors.ErrorResponse;

import feign.Response;
import feign.codec.ErrorDecoder;

@SuppressWarnings("unchecked")
public class CustomErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    switch(response.status()) {
      case 401:
        try {
          ObjectMapper mapper = new ObjectMapper();
          com.jobs.board.listings.response.Response<String> res = mapper.readValue(
            response.body().asInputStream(),
            (Class<com.jobs.board.listings.response.Response<String>>)(Class<?>) com.jobs.board.listings.response.Response.class
          );
          return new ErrorResponse(res.getBody(), res.getStatusCode());
        } catch (Exception exc) {
          return new ErrorResponse(exc.getMessage(), 500);
        }
      case 400:
        try {
          ObjectMapper mapper = new ObjectMapper();
          com.jobs.board.listings.response.Response<String> res = mapper.readValue(
            response.body().asInputStream(),
            (Class<com.jobs.board.listings.response.Response<String>>)(Class<?>) com.jobs.board.listings.response.Response.class
          );
          return new ErrorResponse(res.getBody(), res.getStatusCode());
        } catch (Exception exc) {
          return new ErrorResponse(exc.getMessage(), 500);
        }
      case 500:
        try {
          ObjectMapper mapper = new ObjectMapper();
          com.jobs.board.listings.response.Response<?> res = mapper.readValue(
            response.body().asInputStream(),
            (Class<com.jobs.board.listings.response.Response<?>>)(Class<?>) com.jobs.board.listings.response.Response.class
          );
          return new ErrorResponse("Error occured in client", res.getStatusCode());
        } catch (Exception exc) {
          return new ErrorResponse(exc.getMessage(), 500);
        }
      default:
        return new ErrorResponse("Server error", 500);
    }
  }
}
