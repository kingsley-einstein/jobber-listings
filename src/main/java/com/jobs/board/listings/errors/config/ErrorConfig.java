package com.jobs.board.listings.errors.config;

import com.jobs.board.listings.errors.decoder.CustomErrorDecoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

@Configuration
public class ErrorConfig {
  @Bean
  public ErrorDecoder decoder() {
    return new CustomErrorDecoder();
  }
}
