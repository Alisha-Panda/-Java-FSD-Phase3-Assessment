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
	
	public List<Product> getEntityProducts(Integer pageNo, Integer pageSize, String sortBy, String productName) throws Exception{
		List<Product> product = new ArrayList<Product>();
		Page<Product> pagedResult;
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));
			System.out.println(productName);
			if(productName == null) {
				pagedResult =  productRepository.findAll(paging);
			}
			else {
				pagedResult =  productRepository.findByNameContaining(productName, paging);
			}
			
			product = pagedResult.getContent();
			return product;
		}
		catch(Exception ex) {
			throw new Exception("Unable to retrieve products "+ex.getMessage().toString());
		}
	}
	public Optional<Product> getEntityProduct(int productId) throws Exception{
		try {
			Optional<Product> product = this.productRepository.findById(productId);
			return product;
		}
		catch(Exception ex){
			throw new Exception("Unable to retrieve product with id"+productId+" "+ex.getMessage().toString());
		}
	}

	public void addEntityProduct(Product addProduct) throws Exception {
		try {
			this.productRepository.save(addProduct);
		}
		catch(Exception ex) {
			throw new Exception("Unable to add product "+ex.getMessage().toString());
		}
	}
	
	public void updateEntityProduct(Product updateProduct) throws Exception {
		try {
			this.productRepository.save(updateProduct);
		}
		catch(Exception ex) {
			throw new Exception("Unable to update product "+ex.getMessage().toString());
		}
	}
	
	public void deleteEntityProduct(int productId) throws Exception {
		try {
			this.productRepository.deleteById(productId);
		}
		catch(Exception ex) {
			throw new Exception("Unable to delete product "+ex.getMessage().toString());
		}
	}

}
