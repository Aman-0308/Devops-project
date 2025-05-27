package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	//Write the appropriate methods if required
}
