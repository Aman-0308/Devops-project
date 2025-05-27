package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.infy.dto.CustomerCartDTO;
import com.infy.dto.MedicineDTO;
import com.infy.entity.CustomerCart;
import com.infy.exception.EPharmacyException;
import com.infy.repository.CustomerCartRepository;

@Service(value = "customerCartService")
@Transactional
public class CustomerCartServiceImpl implements CustomerCartService {

	@Autowired
	private CustomerCartRepository customerCartRepository;
	
	@Autowired
	private RestTemplate template;

	@Override
	public void addMedicinesToCart(CustomerCartDTO customerCartDTO, Integer medicineId, Integer customerId)
			throws EPharmacyException {

		// Check if the medicine exists in the Medicine service
		MedicineDTO medicine = template.getForObject("http://localhost:6200/epharmacy/medicine-api/medicines/" + medicineId, MedicineDTO.class);
		if (medicine == null) {
			throw new EPharmacyException("CustomerCartService.MEDICINE_NOT_FOUND");
		}

		// Check if the medicine is already in the cart
		CustomerCart existingCart = customerCartRepository.findByCustomerIdAndMedicineId(customerId, medicineId);
		if (existingCart != null) {
			// Update the quantity if the medicine is already in the cart
			existingCart.setQuantity(existingCart.getQuantity() + customerCartDTO.getQuantity());
			customerCartRepository.save(existingCart);
		} else {
			// Add a new entry to the cart
			CustomerCart newCart = new CustomerCart();
			newCart.setCustomerId(customerId);
			newCart.setMedicineId(medicineId);
			newCart.setQuantity(customerCartDTO.getQuantity());
			customerCartRepository.save(newCart);
		}
	}

	@Override
	public List<CustomerCartDTO> getMedicinesFromCart(Integer customerId) throws EPharmacyException {
		List<CustomerCart> customerCartList = customerCartRepository.findByCustomerId(customerId);
		if (customerCartList.isEmpty()) {
			throw new EPharmacyException("CustomerCartService.EMPTY_CART");
		}
		List<CustomerCartDTO> ccdList = new ArrayList<>();
		for (CustomerCart cc : customerCartList) {
			CustomerCartDTO ccDTO = new CustomerCartDTO();
			ccDTO.setCartId(cc.getCartId());
			ccDTO.setCustomerId(customerId);
			MedicineDTO m = template.getForObject("http://localhost:6200/epharmacy/medicine-api/medicines/"+cc.getMedicineId(), MedicineDTO.class);
			ccDTO.setMedicine(m);;
			ccDTO.setQuantity(cc.getQuantity());
			ccdList.add(ccDTO);
		}
		return ccdList;
	}

	@Override
	public void modifyQuantityOfMedicinesInCart(Integer customerId, Integer medicineId, Integer quantity)
			throws EPharmacyException {
		if (quantity < 1) {
			throw new EPharmacyException("CustomerCartService.INVALID_QUANTITY");
		}

		CustomerCart customerCart = customerCartRepository.findByCustomerIdAndMedicineId(customerId, medicineId);
		if (customerCart == null) {
			throw new EPharmacyException("CustomerCartService.MEDICINE_NOT_FOUND");
		}

		customerCart.setQuantity(quantity);
		customerCartRepository.save(customerCart);
	}

	@Override
	public void deleteMedicineFromCart(Integer customerId, Integer medicineId) throws EPharmacyException {
		CustomerCart customerCart = customerCartRepository.findByCustomerIdAndMedicineId(customerId, medicineId);
		if (customerCart == null) {
			throw new EPharmacyException("CustomerCartService.MEDICINE_NOT_FOUND");
		}

		customerCartRepository.delete(customerCart);
	}

	@Override
	public void deleteAllMedicinesFromCart(Integer customerId) throws EPharmacyException {
		List<CustomerCart> customerCartList = customerCartRepository.findByCustomerId(customerId);
		if (customerCartList.isEmpty()) {
			throw new EPharmacyException("CustomerCartService.EMPTY_CART");
		}

		customerCartRepository.deleteAll(customerCartList);
	}

}