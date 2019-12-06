package com.jobs.board.listings.client.responses;

import com.jobs.board.listings.client.mappings.Auth;
import com.jobs.board.listings.response.Response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface AuthResponse {
  @GetMapping("/api/v1/auth/getLoggedUser")
  public Response<Auth> getLoggedUser(@RequestHeader(value = "Authorization") String authorization);
}
