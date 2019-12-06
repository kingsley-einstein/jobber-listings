package com.jobs.board.listings.config;

import com.jobs.board.listings.config.customizers.CustomRestTemplateCustomizer;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class RestTemplateConfig {
  @Bean
  @DependsOn(value = {"customizer"})
  public RestTemplateBuilder builder() {
    return new RestTemplateBuilder(customizer());
  }

  @Bean
  public CustomRestTemplateCustomizer customizer() {
    return new CustomRestTemplateCustomizer();
  }
}
