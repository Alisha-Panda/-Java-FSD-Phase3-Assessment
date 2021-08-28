package com.dell.webservice.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.webservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

}
