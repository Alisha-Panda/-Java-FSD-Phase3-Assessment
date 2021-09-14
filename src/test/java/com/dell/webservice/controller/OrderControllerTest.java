package com.dell.webservice.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.dell.webservice.entity.Order;
import com.dell.webservice.entity.Product;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Order Controller Test")
public class OrderControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int randomPort;
	
	@Test
	@DisplayName("Get all order")
	public void testGetOrder() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/order/getorders";
		ResponseEntity<List> response = testRestTemplate.getForEntity(url,List.class);
		assertEquals(200,response.getStatusCode().value());
		assertEquals(false, response.getBody().isEmpty());
	}
	
	@Test
	@DisplayName("Get order by id")
	public void testGetOrderById() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/order/getorder/40";
		ResponseEntity<Order> response = testRestTemplate.getForEntity(url,Order.class);
		assertEquals(200,response.getStatusCode().value());
		assertEquals(40, response.getBody().getId());
		assertEquals("Alisha", response.getBody().getName());
		assertEquals("abcd@gmail.com", response.getBody().getEmail());
	}
	
	@Test
	@DisplayName("Add order")
	public void testAddOrder() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/order/addorder?userName=Tanuja";
		Product product = new Product("Puma Cell Endura",55447.0,"Puma Cell Endura","PUMA","Running and Training","Amazon",new Date());
		Set<Product> productList = new HashSet<Product>(); 
		productList.add(product);
		Order order = new Order("Alisha",0.0,"abcd@gmail.com","3B 201, Soul Space Arista, ORR, Bangalore","9876564534",productList);
		
		HttpEntity<Order> requestObj = new HttpEntity<>(order);
		ResponseEntity<Order> response = testRestTemplate.postForEntity(url,requestObj,Order.class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode().value());
		assertEquals(201,response.getStatusCode().value());
		assertEquals("Alisha", response.getBody().getName());
		assertEquals("abcd@gmail.com", response.getBody().getEmail());
	}
	
	@Test
	@DisplayName("Update order") 
	public void testUpdateOrder() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/order/updateorder/40?userName=Tanuja";
		
		Product product = new Product(27,"Puma Cell Endura",55447.0,"Puma Cell Endura","PUMA","Running and Training","Amazon",new Date());
		Set<Product> productList = new HashSet<Product>(); 
		productList.add(product);
		Order order = new Order(40,"Alisha",0.0,"abcd@gmail.com","3B 201, Soul Space Arista, ORR, Bangalore","9876564534",productList);
		
		HttpEntity<Order> requestObj = new HttpEntity<>(order);
		ResponseEntity<Order> response = testRestTemplate.exchange(url,HttpMethod.PUT,requestObj,Order.class);
		
		assertEquals(204,response.getStatusCode().value());

	}
	
	@Test
	@DisplayName("Delete order")
	public void testDeleteOrder() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/order/deleteorder/45?userName=Tanuja";
		testRestTemplate.delete(url,Order.class);
	}

}
