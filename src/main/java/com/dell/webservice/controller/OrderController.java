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

import com.dell.webservice.entity.Order;
import com.dell.webservice.repository.OrderService;


@RestController
@RequestMapping("api/v1/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/getorders")
	public ResponseEntity<List<Order>> getOrders(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
		List<Order> list = orderService.getEntityOrders(pageNo, pageSize, sortBy);
		return new ResponseEntity<List<Order>>(list, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@GetMapping("/getorder/{orderId}")
	public ResponseEntity<Optional<Order>> getOrder(@PathVariable("orderId") int id) {
		return new ResponseEntity<Optional<Order>>(this.orderService.getEntityOrder(id),new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/addorder")
	public ResponseEntity<Order> addOrder(@RequestBody(required = false) Order addOrder){
		this.orderService.addEntityOrder(addOrder);
		return new ResponseEntity<Order>(addOrder, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateorder/{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable("orderId") int id, @RequestBody(required = false) Order updateOrder) {
		if(id != updateOrder.getId()) {
			return new ResponseEntity<Order>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		this.orderService.updateEntityOrder(updateOrder);
		return new ResponseEntity<Order>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/deleteorder/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("orderId") int id){
		Object getOrder = this.orderService.getEntityOrder(id);
		if(getOrder == null) {
			return new ResponseEntity<Order>(new HttpHeaders(), HttpStatus.NOT_FOUND);
		}
		else {
			this.orderService.deleteEntityOrder(id);
			return new ResponseEntity<Order>(new HttpHeaders(), HttpStatus.NO_CONTENT);
		}
		
	}
}
