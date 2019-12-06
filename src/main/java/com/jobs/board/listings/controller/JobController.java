package com.jobs.board.listings.controller;

import java.util.List;
import java.util.UUID;

import com.jobs.board.listings.client.mappings.Auth;
import com.jobs.board.listings.client.responses.AuthResponse;
import com.jobs.board.listings.errors.ErrorResponse;
import com.jobs.board.listings.model.Job;
import com.jobs.board.listings.repository.JobRepository;
import com.jobs.board.listings.response.Response;
import com.jobs.board.listings.types.JobType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/getAllCreatedListings/{page}")
  public ResponseEntity<Response<List<Job>>> getAllListingsByUser(@RequestHeader(value = "Authorization") String authorization, @PathVariable("page") Integer page) {
    Response<Auth> authResponse = response.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    Page<Job> pagedJob = repository.findByCreatedBy(auth.getId(), PageRequest.of(page, 10)).orElseThrow(() -> {
      return new ErrorResponse("Not Found", 404);
    });
    Response<List<Job>> listOfJobs = new Response<>(200, pagedJob.getContent());
    return new ResponseEntity<>(listOfJobs, HttpStatus.OK);
  }

  @GetMapping("/countByMe")
  public ResponseEntity<Response<Long>> countAllListingsByUser(@RequestHeader(value = "Authorization") String authorization) {
    Response<Auth> authResponse = response.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    Response<Long> countOfJobs = new Response<>(200, repository.countByCreatedBy(auth.getId()));
    return new ResponseEntity<>(countOfJobs, HttpStatus.OK);
  }

  @GetMapping("/getAllListings/{page}")
  public ResponseEntity<Response<List<Job>>> getAllListings(@PathVariable("page") Integer page) {
    Page<Job> pagedJob = repository.findAll(PageRequest.of(page, 20));
    Response<List<Job>> listOfJobs = new Response<>(200, pagedJob.getContent());
    return new ResponseEntity<>(listOfJobs, HttpStatus.OK);
  }

  @GetMapping("/countAllListings")
  public ResponseEntity<Response<Long>> countAllListings() {
    Response<Long> countOfJobs = new Response<>(200, repository.count());
    return new ResponseEntity<>(countOfJobs, HttpStatus.OK);
  }

  @GetMapping("/getListingsByType/{type}")
  public ResponseEntity<Response<List<Job>>> getAllListingsByType(@PathVariable("type") String type) {
    Page<Job> pagedJob = repository.findByType(JobType.valueOf(type)).orElseThrow(() -> {
      return new ErrorResponse("Not Found", 404);
    });
    Response<List<Job>> listOfJobs = new Response<>(200, pagedJob.getContent());
    return new ResponseEntity<>(listOfJobs, HttpStatus.OK);
  }

  @PatchMapping("/updateListing/{id}")
  public ResponseEntity<Response<Job>> updateListing(@PathVariable("id") UUID id, @RequestBody Job body, @RequestHeader("Authorization") String authorization) {
    Response<Auth> authResponse = response.getLoggedUser(authorization);
    Auth auth = authResponse.getBody();
    Job job = repository.findByCreatedByAndId(auth.getId(), id).orElseThrow(() -> {
      return new ErrorResponse("Not Found", 404);
    });
    job.setJob(body);
    Job saved = repository.save(job);
    Response<Job> updatedJob = new Response<>(200, saved);
    return new ResponseEntity<>(updatedJob, HttpStatus.OK);
  }
}
