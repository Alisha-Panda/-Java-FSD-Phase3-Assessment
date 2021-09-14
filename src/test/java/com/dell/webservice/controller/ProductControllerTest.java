package com.dell.webservice.controller;

import java.util.Date;
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

import com.dell.webservice.entity.Product;
import com.dell.webservice.entity.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Product Controller Test")
public class ProductControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int randomPort;
	
	@Test
	@DisplayName("Get all products")
	public void testGetUser() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/product/getproducts";
		ResponseEntity<List> response = testRestTemplate.getForEntity(url,List.class);
		assertEquals(200,response.getStatusCode().value());
		assertEquals(false, response.getBody().isEmpty());
	}
	
	@Test
	@DisplayName("Get product by id")
	public void testGetUserById() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/product/getproduct/31?userName=Tanuja";
		ResponseEntity<Product> response = testRestTemplate.getForEntity(url,Product.class);
		assertEquals(200,response.getStatusCode().value());
		assertEquals(31, response.getBody().getId());
		assertEquals("Adidas ZETY", response.getBody().getName());
		assertEquals("Adidas", response.getBody().getBrand());
	}
	
	@Test
	@DisplayName("Add user")
	public void testAddUser() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/product/addproduct?userName=Tanuja";
		Product product = new Product("Puma Cell Endura",5547.0,"Puma Cell Endura","PUMA","Running and Training","Amazon",new Date());
		
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		ResponseEntity<Product> response = testRestTemplate.postForEntity(url,requestObj,Product.class);
		
		assertEquals(201,response.getStatusCode().value());
		assertEquals("Puma Cell Endura", response.getBody().getName());
		assertEquals("PUMA", response.getBody().getBrand());
	}
	
	@Test
	@DisplayName("Update product") 
	public void testUpdateProduct() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/product/updateproduct/27?userName=Tanuja";
		Product product = new Product(27,"Adidas XYZB",65447.0,"Adidas XYZB","Adidas","Running and Training","Amazon",new Date());
		
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		ResponseEntity<Product> response = testRestTemplate.exchange(url,HttpMethod.PUT,requestObj,Product.class);
		
		assertEquals(204,response.getStatusCode().value());

	}
	
	@Test
	@DisplayName("Delete product")
	public void testDeleteProduct() {
		
		String url = "http://localhost:"+randomPort+"/api/v1/product/deleteproduct/37?userName=Tanuja";
		testRestTemplate.delete(url,Product.class);
	}

}
