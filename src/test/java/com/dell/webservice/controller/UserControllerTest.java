package com.dell.webservice.controller;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.dell.webservice.entity.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("User Controller Test")
public class UserControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int randomPort;
	
	@Test
	@DisplayName("Get all users")
	public void testGetUser() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/user/getusers";
		ResponseEntity<List> response = testRestTemplate.getForEntity(url,List.class);
		assertEquals(200,response.getStatusCode().value());
		assertEquals(false, response.getBody().isEmpty());
	}
	
	@Test
	@DisplayName("Get user by id")
	public void testGetUserById() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/user/getuser/18";
		ResponseEntity<User> response = testRestTemplate.getForEntity(url,User.class);
		assertEquals(200,response.getStatusCode().value());
		assertEquals(18, response.getBody().getId());
		assertEquals("Tanuja", response.getBody().getUsername());
		assertEquals("admin", response.getBody().getRole());
	}
	
	@Test
	@DisplayName("Add user")
	public void testAddUser() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/user/adduser";
		User user = new User("Tanuja","abcd","Tanuja.Lavu@dell.com","admin",10000.57,true);
		
		HttpEntity<User> requestObj = new HttpEntity<>(user);
		ResponseEntity<User> response = testRestTemplate.postForEntity(url,requestObj,User.class);
		
		assertEquals(201,response.getStatusCode().value());
		assertEquals("Tanuja", response.getBody().getUsername());
		assertEquals("admin", response.getBody().getRole());
	}
	
	@Test
	@DisplayName("Update user")
	public void testUpdateUser() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/user/updateuser/16";
		User user = new User(16,"Avilasa","abcd","Avilasa.Das@dell.com","user",10000.57,true);
		
		HttpEntity<User> requestObj = new HttpEntity<>(user);
		ResponseEntity<User> response = testRestTemplate.exchange(url,HttpMethod.PUT,requestObj,User.class);
		
		assertEquals(204,response.getStatusCode().value());

	}
	
	@Test
	@DisplayName("Delete user")
	public void testDeleteUser() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/user/deleteuser/13";
		testRestTemplate.delete(url,User.class);
	}

}
