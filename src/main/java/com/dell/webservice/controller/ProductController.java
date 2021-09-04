package com.dell.webservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dell.webservice.entity.Product;
import com.dell.webservice.repository.ProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/getproducts")
	public ResponseEntity<List<Product>> getProducts(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
		List<Product> list = productService.getEntityProducts(pageNo, pageSize, sortBy);
		return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@GetMapping("/getproduct/{productId}")
	public ResponseEntity<Optional<Product>> getProduct(@PathVariable("productId") int id) {
		return new ResponseEntity<Optional<Product>>(this.productService.getEntityProduct(id),new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@RequestBody(required = false) Product addProduct){
		this.productService.addEntityProduct(addProduct);
		return new ResponseEntity<Product>(addProduct, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateproduct/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") int id, @RequestBody(required = false) Product updateProduct) {
		if(id != updateProduct.getId()) {
			return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		this.productService.updateEntityProduct(updateProduct);
		return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/deleteproduct/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") int id){
		Object getProduct = this.productService.getEntityProduct(id);
		if(getProduct == null) {
			return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		else {
			this.productService.deleteEntityProduct(id);
			return new ResponseEntity<Product>(new HttpHeaders(), HttpStatus.NO_CONTENT);
		}
		
	}

}
