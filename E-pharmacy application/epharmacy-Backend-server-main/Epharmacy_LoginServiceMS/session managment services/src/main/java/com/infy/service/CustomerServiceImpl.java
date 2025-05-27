package com.infy.service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.ChangePasswordDTO;
import com.infy.dto.CustomerAddressDTO;
import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.entity.CustomerAddress;
import com.infy.exception.EPharmacyException;
import com.infy.repository.CustomerRepository;
import com.infy.repository.CustomerAddressRepository;
import com.infy.utility.HashingUtility;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	
	@Override
	public CustomerDTO authenticateCustomer(String emailId, String password)
			throws EPharmacyException, NoSuchAlgorithmException {
		CustomerDTO customerDTO = null;
		Customer customer = customerRepository.findByCustomerEmailId(emailId);
		if (customer != null) {
			customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setCustomerEmailId(customer.getCustomerEmailId());
			customerDTO.setCustomerName(customer.getCustomerName());
			customerDTO.setContactNumber(customer.getContactNumber());
			customerDTO.setPassword(customer.getPassword());

			List<CustomerAddressDTO> addressList = new ArrayList<>();
			for (CustomerAddress ca : customer.getAddressList()) {
				CustomerAddressDTO caDTO = new CustomerAddressDTO();
				caDTO.setAddressId(ca.getAddressId());
				caDTO.setAddressName(ca.getAddressName());
				caDTO.setAddressLine1(ca.getAddressLine1());
				caDTO.setAddressLine2(ca.getAddressLine2());
				caDTO.setArea(ca.getArea());
				caDTO.setCity(ca.getCity());
				caDTO.setState(ca.getState());
				caDTO.setPincode(ca.getPincode());

				addressList.add(caDTO);
			}
			customerDTO.setAddressList(addressList);
		}
		if (customerDTO == null) {
			throw new EPharmacyException("CustomerService.INVALID_CREDENTIALS");
		}

		String passwordFromDB = customer.getPassword();
		if (passwordFromDB != null) {
			String hashedPassword = HashingUtility.getHashValue(password);

			if (hashedPassword.equals(passwordFromDB)) {
				customerDTO.setPassword(null);
				return customerDTO;
			} else {
				throw new EPharmacyException("CustomerService.INVALID_CREDENTIALS");
			}
		} else {
			throw new EPharmacyException("CustomerService.INVALID_CREDENTIALS");
		}

	}

	// This method will add a new customer
	@Override
	public String registerNewCustomer(CustomerDTO customerDTO) throws EPharmacyException, NoSuchAlgorithmException {
		if (!customerDTO.getCustomerName().matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
			throw new EPharmacyException("CustomerService.INVALID_NAME");
		}
		if (!customerDTO.getContactNumber().matches("^[6-9][0-9]{9}$")) {
			throw new EPharmacyException("CustomerService.INVALID_CONTACT_NUMBER");
		}

		Customer customer = new Customer();
		customer.setCustomerName(customerDTO.getCustomerName());
		customer.setCustomerEmailId(customerDTO.getCustomerEmailId());
		customer.setContactNumber(customerDTO.getContactNumber());
		customer.setPassword(HashingUtility.getHashValue(customerDTO.getPassword()));

		customerRepository.save(customer);
		return "Customer registered successfully.";
	}

	@Override
	public void addCustomerAddress(CustomerAddressDTO addressDTO, Integer customerId) throws EPharmacyException {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EPharmacyException("CustomerService.CUSTOMER_NOT_FOUND"));

		CustomerAddress address = new CustomerAddress();
		address.setAddressName(addressDTO.getAddressName());
		address.setAddressLine1(addressDTO.getAddressLine1());
		address.setAddressLine2(addressDTO.getAddressLine2());
		address.setArea(addressDTO.getArea());
		address.setCity(addressDTO.getCity());
		address.setState(addressDTO.getState());
		address.setPincode(addressDTO.getPincode());
		address.setCustomer(customer);

		customer.getAddressList().add(address);
		customerRepository.save(customer);
	}

	@Override
	public CustomerDTO viewCustomer(Integer customerId) throws EPharmacyException {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EPharmacyException("CustomerService.CUSTOMER_NOT_FOUND"));
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setCustomerName(customer.getCustomerName());
		customerDTO.setCustomerEmailId(customer.getCustomerEmailId());
		customerDTO.setContactNumber(customer.getContactNumber());

		List<CustomerAddressDTO> addressList = new ArrayList<>();
		for (CustomerAddress ca : customer.getAddressList()) {
			CustomerAddressDTO caDTO = new CustomerAddressDTO();
			caDTO.setAddressId(ca.getAddressId());
			caDTO.setAddressName(ca.getAddressName());
			caDTO.setAddressLine1(ca.getAddressLine1());
			caDTO.setAddressLine2(ca.getAddressLine2());
			caDTO.setArea(ca.getArea());
			caDTO.setCity(ca.getCity());
			caDTO.setState(ca.getState());
			caDTO.setPincode(ca.getPincode());

			addressList.add(caDTO);
		}
		customerDTO.setAddressList(addressList);
		return customerDTO;
	}

	@Override
	public void deleteAddress(Integer addressId) throws EPharmacyException {
		CustomerAddress address = customerAddressRepository.findById(addressId).orElseThrow(() -> new EPharmacyException("CustomerService.ADDRESS_NOT_FOUND"));
		customerAddressRepository.delete(address);
	}

	@Override
	public List<CustomerAddressDTO> viewAllAddress(Integer customerId) throws EPharmacyException {
		Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EPharmacyException("CustomerService.CUSTOMER_NOT_FOUND"));

		List<CustomerAddressDTO> addressList = new ArrayList<>();
		for (CustomerAddress ca : customer.getAddressList()) {
			CustomerAddressDTO caDTO = new CustomerAddressDTO();
			caDTO.setAddressId(ca.getAddressId());
			caDTO.setAddressName(ca.getAddressName());
			caDTO.setAddressLine1(ca.getAddressLine1());
			caDTO.setAddressLine2(ca.getAddressLine2());
			caDTO.setArea(ca.getArea());
			caDTO.setCity(ca.getCity());
			caDTO.setState(ca.getState());
			caDTO.setPincode(ca.getPincode());

			addressList.add(caDTO);
		}
		return addressList;
	}

	@Override
	public String updateCustomerDetails(CustomerDTO dto) throws EPharmacyException {
		Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new EPharmacyException("CustomerService.CUSTOMER_NOT_FOUND"));

		if (!dto.getCustomerName().matches("^[A-Za-z]+( [A-Za-z]+)*$")) {
			throw new EPharmacyException("CustomerService.INVALID_NAME");
		}
		if (!dto.getContactNumber().matches("^[6-9][0-9]{9}$")) {
			throw new EPharmacyException("CustomerService.INVALID_CONTACT_NUMBER");
		}

		customer.setCustomerName(dto.getCustomerName());
		customer.setCustomerEmailId(dto.getCustomerEmailId());
		customer.setContactNumber(dto.getContactNumber());

		customerRepository.save(customer);
		return "Customer details updated successfully.";
	}

	@Override
	public void changePassword(ChangePasswordDTO changePasswordDTO) throws EPharmacyException{
		Customer customer = customerRepository.findById(changePasswordDTO.getCustomerId()).orElseThrow(() -> new EPharmacyException("CustomerService.CUSTOMER_NOT_FOUND"));

		String newPassword = changePasswordDTO.getNewPassword();
		if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,20}$")) {
			throw new EPharmacyException("CustomerService.INVALID_NEW_PASSWORD");
		}

		try {
			customer.setPassword(HashingUtility.getHashValue(newPassword));
		} catch (NoSuchAlgorithmException e) {
			throw new EPharmacyException("CustomerService.HASHING_ERROR");
		}
		customerRepository.save(customer);
	}

	@Override
    public LocalDate upgradeCustomerToPrime(CustomerDTO customerDTO) throws EPharmacyException {
        throw new EPharmacyException("CustomerService.INVALID_PLAN");
    }
}


