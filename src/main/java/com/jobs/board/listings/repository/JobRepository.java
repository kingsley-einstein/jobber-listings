package com.jobs.board.listings.repository;

import java.util.Optional;
import java.util.UUID;

import com.jobs.board.listings.model.Job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, UUID> {
  public Optional<Page<Job>> findByCreatedBy(UUID createdBy, Pageable pageable);
  public Long countByCreatedBy(UUID createdBy);
}
