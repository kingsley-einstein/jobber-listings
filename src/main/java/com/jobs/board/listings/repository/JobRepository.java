package com.jobs.board.listings.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.jobs.board.listings.model.Job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, UUID> {
  public Optional<List<Job>> findByCreatedBy(UUID createdBy);
}
