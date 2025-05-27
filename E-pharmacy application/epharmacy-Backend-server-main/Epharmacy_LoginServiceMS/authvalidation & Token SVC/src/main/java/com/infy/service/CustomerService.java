package com.infy.service;

import java.time.LocalDate;
import java.util.List;

import com.infy.dto.ChangePasswordDTO;
import com.infy.dto.CustomerAddressDTO;
import com.infy.dto.CustomerDTO;
import com.infy.exception.EPharmacyException;

public interface CustomerService {

	CustomerDTO authenticateCustomer(String emailId, String password) throws Exception;

	String registerNewCustomer(CustomerDTO customerDTO) throws Exception;

	void addCustomerAddress(CustomerAddressDTO caDTO, Integer customerId) throws EPharmacyException;

	List<CustomerAddressDTO> viewAllAddress(Integer customerId) throws EPharmacyException;

	String updateCustomerDetails(CustomerDTO dto) throws EPharmacyException;

	CustomerDTO viewCustomer(Integer CustomerId) throws EPharmacyException;

	void deleteAddress(Integer addressId) throws EPharmacyException;
	
	void changePassword(ChangePasswordDTO changePasswordDTO) throws Exception;
	
	LocalDate upgradeCustomerToPrime(CustomerDTO customerDTO) throws EPharmacyException;
	
}
