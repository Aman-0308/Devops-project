package com.infy.api;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.ChangePasswordDTO;
import com.infy.dto.CustomerAddressDTO;
import com.infy.dto.CustomerDTO;
import com.infy.exception.EPharmacyException;
import com.infy.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping(value = "/customer-api")
public class CustomerAPI {

	@Autowired
	private CustomerService customerService;

	static Log logger = LogFactory.getLog(CustomerAPI.class);

	@PostMapping(value = "customer/login")
	public ResponseEntity<CustomerDTO> authenticateCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
		CustomerDTO customerDTOFromDB = customerService.authenticateCustomer(customerDTO.getCustomerEmailId(),
				customerDTO.getPassword());
		return new ResponseEntity<>(customerDTOFromDB, HttpStatus.OK);
	}
	
	@PostMapping(value = "customer/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        String response = customerService.registerNewCustomer(customerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "customer/view")
    public ResponseEntity<CustomerDTO> viewCustomer(@RequestBody Integer customerId) throws EPharmacyException {
        CustomerDTO customerDTO = customerService.viewCustomer(customerId);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping(value = "customer/viewAllAddress")
    public ResponseEntity<List<CustomerAddressDTO>> viewAllAddress(@RequestBody Integer customerId) throws EPharmacyException {
        List<CustomerAddressDTO> addressList = customerService.viewAllAddress(customerId);
        return new ResponseEntity<>(addressList, HttpStatus.OK);
    }

    @PostMapping(value = "customer/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody CustomerAddressDTO caDTO, @RequestBody Integer customerId) throws EPharmacyException {
        customerService.addCustomerAddress(caDTO, customerId);
        return new ResponseEntity<>("Address added successfully.", HttpStatus.CREATED);
    }

    @PostMapping(value = "customer/update")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO cdto) throws EPharmacyException {
        String response = customerService.updateCustomerDetails(cdto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "customer/deleteAddress")
    public ResponseEntity<String> deleteAddress(@RequestBody Integer addressId) throws EPharmacyException {
        customerService.deleteAddress(addressId);
        return new ResponseEntity<>("Address deleted successfully.", HttpStatus.OK);
    }

    @PostMapping(value = "customer/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO cpDTO) throws Exception {
        customerService.changePassword(cpDTO);
        return new ResponseEntity<>("Password changed successfully.", HttpStatus.OK);
    }

    @PostMapping(value = "customer/upgrade")
    public ResponseEntity<String> upgradeCustomer(@RequestBody CustomerDTO custDTO) throws Exception {
        LocalDate expiryDate = customerService.upgradeCustomerToPrime(custDTO);
        return new ResponseEntity<>("Customer upgraded to prime. Expiry date: " + expiryDate, HttpStatus.OK);
    }
}
