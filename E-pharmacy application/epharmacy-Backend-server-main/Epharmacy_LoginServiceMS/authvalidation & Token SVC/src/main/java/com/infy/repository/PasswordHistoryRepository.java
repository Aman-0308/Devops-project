package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.PasswordHistory;

public interface PasswordHistoryRepository extends CrudRepository<PasswordHistory, Integer>{
	PasswordHistory findTopByCustomerIdOrderByChangeDateDesc(Integer customerId);
}
