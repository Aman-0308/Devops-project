package com.infy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.CustomerCart;

 public interface CustomerCartRepository extends CrudRepository<CustomerCart, Integer> {
	List<CustomerCart> findByCustomerId(Integer customerId);
	
    CustomerCart findByCustomerIdAndMedicineId(Integer customerId, Integer medicineId);
}  