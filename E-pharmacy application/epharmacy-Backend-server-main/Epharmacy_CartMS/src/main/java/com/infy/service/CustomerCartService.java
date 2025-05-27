package com.infy.service;

import java.util.List;

import com.infy.dto.CustomerCartDTO;
import com.infy.exception.EPharmacyException;


public interface CustomerCartService {

	void addMedicinesToCart(CustomerCartDTO customerCartDTO,Integer medicineId,Integer customerId) throws EPharmacyException;

	List<CustomerCartDTO> getMedicinesFromCart(Integer customerId) throws EPharmacyException;

	void modifyQuantityOfMedicinesInCart(Integer customerId, Integer medicineId, Integer quantity)
			throws EPharmacyException;

	void deleteMedicineFromCart(Integer customerId, Integer medicineId) throws EPharmacyException;

	void deleteAllMedicinesFromCart(Integer customerId) throws EPharmacyException;

}