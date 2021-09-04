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

import com.dell.webservice.entity.Product;
import com.dell.webservice.entity.User;
import com.dell.webservice.interfaces.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getEntityProducts(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
		Page<Product> pagedResult =  productRepository.findAll(paging);
		if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
	}
	public Optional<Product> getEntityProduct(int productId){
	
//		if(getUser != null) {
//		}
		return this.productRepository.findById(productId);
	}
//	public Product getEntityProductByName(String productName){
//		
////		if(getUser != null) {
////		}
//		List<Product> products = (List<Product>) this.productRepository.findAll();
//		for(Product pr : products) {
//			if(pr.getName() == productName) {
//				Product prod = pr;
//				break;
//			}
//		}
//		return prod;	
//	}
	public void addEntityProduct(Product addProduct) {
//		if(addUser != null) {
//			
//		}
		this.productRepository.save(addProduct);
	}
	
	public void updateEntityProduct(Product updateProduct) {
//		if(addUser != null) {
//			
//		}
		this.productRepository.save(updateProduct);
	}
	
	public void deleteEntityProduct(int productId) {
//		if(addUser != null) {
//			
//		}
		this.productRepository.deleteById(productId);
	}

}
