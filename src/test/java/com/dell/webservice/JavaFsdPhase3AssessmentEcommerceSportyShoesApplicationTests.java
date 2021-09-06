package com.dell.webservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Main Test Application")
class JavaFsdPhase3AssessmentEcommerceSportyShoesApplicationTests {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int randomPort;

	@Test
	void contextLoads() {
	}
	
	@Test
	@DisplayName("Test server is running")
	void testForRunningServer() {
		String url = "http://localhost:"+randomPort+"/";
		ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
		
		assertEquals("Spring application server is up and running", response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}
}
