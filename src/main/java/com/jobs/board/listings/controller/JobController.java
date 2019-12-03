package com.jobs.board.listings.controller;

import java.util.UUID;

import com.jobs.board.listings.client.mappings.Auth;
import com.jobs.board.listings.client.responses.AuthResponse;
import com.jobs.board.listings.model.Job;
import com.jobs.board.listings.repository.JobRepository;
import com.jobs.board.listings.response.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {
  @Autowired
  private JobRepository repository;

  @Autowired
  private AuthResponse response;

  @PostMapping("/create")
  public ResponseEntity<Response<Job>> createJob(@RequestHeader(value = "Authorization") String authorization, @RequestBody Job body) {
    Response<Auth> authResponse = response.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    Job job = repository.save(
      new Job(UUID.randomUUID(), body.getTitle(), body.getDescription(), body.getType(), auth.getId(), body.getLink())
    );
    Response<Job> jobResponse = new Response<>(201, job);
    return new ResponseEntity<>(jobResponse, HttpStatus.CREATED);
  }
}
