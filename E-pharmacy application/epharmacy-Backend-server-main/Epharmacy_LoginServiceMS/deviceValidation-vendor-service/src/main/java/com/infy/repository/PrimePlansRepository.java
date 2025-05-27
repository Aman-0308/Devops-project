package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.PrimePlans;

public interface PrimePlansRepository extends CrudRepository<PrimePlans, Integer>{
	PrimePlans findByPlanId(Integer planId);
}
