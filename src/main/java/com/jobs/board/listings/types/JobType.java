package com.jobs.board.listings.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JobType {
  @JsonProperty("GOVERNMENT")
  GOVERNMENT,
  @JsonProperty("RAILWAY") 
  RAILWAY,
  @JsonProperty("BANK") 
  BANK,
  @JsonProperty("POSTAL") 
  POSTAL,
  @JsonProperty("JUDICIAL")
  JUDICIAL,
  @JsonProperty("TEACHING")
  TEACHING,
  @JsonProperty("ENGINEERING")
  ENGINEERING,
  @JsonProperty("CLERICAL")
  CLERICAL,
  @JsonProperty("MEDICAL")
  MEDICAL
}
