package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Customer findByCustomerEmailId(String customerEmailId);
	Customer findByCustomerEmailIdAndPassword(String customerEmailId, String password);
	
}
