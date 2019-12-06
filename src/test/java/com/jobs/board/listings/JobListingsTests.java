package com.jobs.board.listings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

// import java.util.ArrayList;
// import java.util.Collections;
import java.util.List;

// import com.jobs.board.listings.controller.JobController;
import com.jobs.board.listings.model.Job;
import com.jobs.board.listings.response.Response;

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.Is;
// import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.converter.HttpMessageConverter;
// import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SuppressWarnings("unchecked")
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@TestPropertySource("classpath:bootstrap-test.properties")
@SpringBootTest(classes = JobsListingsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class JobListingsTests {
  // @LocalServerPort
  // private int port;

  @Value("${server.port}")
  private String port;

  @Autowired
  TestRestTemplate restTemplate;

  // @BeforeClass
  // public static void setConverters() {
  //   List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
  //   MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
  //   converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
  //   converters.add(converter);
  // }

	@Test
	public void serviceIsUp() {
    ResponseEntity<Response<String>> responseEntity = restTemplate.getForEntity(
      "/api/v1/job",
      (Class<Response<String>>)(Class<?>) Response.class
    );
    System.out.println(responseEntity.getBody().getBody());
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    assertNotNull(responseEntity.getBody().getBody());
  }
  
  @Test
  public void controllerSearchesForListings() {
    ResponseEntity<Response<List<Job>>> responseEntity = restTemplate.getForEntity(
      "/api/v1/job/getAllListings/0",
      (Class<Response<List<Job>>>)(Class<?>) Response.class
    );
    System.out.println(responseEntity.getBody().getBody());
    List<Job> listOfJobs = responseEntity.getBody().getBody();
    assertThat(listOfJobs, Is.is(
      IsEmptyCollection.emptyCollectionOf(Job.class)
    ));
  }
}
