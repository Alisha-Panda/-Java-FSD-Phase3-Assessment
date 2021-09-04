package com.dell.webservice.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dell.webservice.entity.Order;
import com.dell.webservice.entity.Product;
import com.dell.webservice.interfaces.OrderRepository;
import com.dell.webservice.interfaces.ProductRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	
	public List<Order> getEntityOrders(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Order> pagedResult =  orderRepository.findAll(paging);
		if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Order>();
        }
	}
	public Optional<Order> getEntityOrder(int orderId){
	
//		if(getUser != null) {
//		}
		return this.orderRepository.findById(orderId);
	}
	public void addEntityOrder(Order addOrder) {
//		if(addUser != null) {
//			
//		}
		double total = 0;
		for( Product prod : addOrder.getProducts()) {
			total = total+ prod.getPrice();
		}
		addOrder.setTotalPrice(total);
		this.orderRepository.save(addOrder);
	}
	
	public void updateEntityOrder(Order updateOrder) {
//		if(addUser != null) {
//			
//		}
		this.orderRepository.save(updateOrder);
	}
	
	public void deleteEntityOrder(int orderId) {
//		if(addUser != null) {
//			
//		}
		this.orderRepository.deleteById(orderId);
	}
}
