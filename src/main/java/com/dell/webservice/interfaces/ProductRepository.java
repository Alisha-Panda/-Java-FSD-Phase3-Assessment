package com.dell.webservice.interfaces;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dell.webservice.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product,Integer>{

}
