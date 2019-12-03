package com.jobs.board.listings.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.jobs.board.listings.types.JobType;

@SuppressWarnings("serial")
@Entity
@Table(name = "jobs")
public class Job implements java.io.Serializable {
  @Id
  private UUID id;

  @NotEmpty(message = "Job title is required")
  @Column(name = "title", nullable = false)
  private String title;

  @NotEmpty(message = "Job description is required")
  @Column(name = "description", nullable = false, columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  private JobType type;

  @Column(name = "createdBy", nullable = false)
  private UUID createdBy;

  @Column(name = "link", nullable = false)
  private String link;

  public Job() {}

  public Job(UUID id, String title, String description, JobType type, UUID createdBy, String link) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.createdBy = createdBy;
    this.link = link;
  }

  public UUID getId() {
    return id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setType(JobType type) {
    this.type = type;
  }

  public JobType getType() {
    return type;
  }

  public UUID getCreatedBy() {
    return createdBy;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getLink() {
    return link;
  }
}
