package com.jobs.board.listings.config.customizers;

import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {
  @Override
  public void customize(RestTemplate template) {
    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
    converters.add(converter);
    template.setMessageConverters(converters);
  }
}

