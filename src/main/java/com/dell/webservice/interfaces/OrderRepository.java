package com.dell.webservice.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.webservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
