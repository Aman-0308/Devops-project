package com.infy.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDate;

import com.infy.dto.CardDTO;
import com.infy.dto.PaymentDTO;
import com.infy.exception.EPharmacyException;
import com.infy.service.PaymentService;

@RequestMapping(value = "payment-api")
public class PaymentAPI {
	
	@Autowired
	private PaymentService paymentService;

	@PostMapping(value = "payment/amount/{amountToPay}")
	public ResponseEntity<String> makePayment(@RequestBody CardDTO cardDTO, @PathVariable Float amountToPay) throws Exception {
	    try {
	        paymentService.processPayment(cardDTO, amountToPay);
	        return new ResponseEntity<>("Payment made successfully.", HttpStatus.CREATED);
	    } catch (EPharmacyException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}

	@GetMapping(value = "payment/details/{paymentId}")
	public ResponseEntity<PaymentDTO> getPaymentDetails(@PathVariable Integer paymentId) throws EPharmacyException {
		return new ResponseEntity<PaymentDTO>(paymentService.getPaymentDetails(paymentId), HttpStatus.OK);
	}
	
	@GetMapping(value = "payment/view-cards/{customerId}")
	public ResponseEntity<List<CardDTO>> viewCards(@PathVariable Integer customerId) throws EPharmacyException {
	    try {
	        List<CardDTO> cards = paymentService.getCardsByCustomerId(customerId);
	        return new ResponseEntity<>(cards, HttpStatus.OK);
	    } catch (EPharmacyException e) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}

	@PostMapping(value = "payment/add-card/{customerId}")
	public ResponseEntity<String> addCardForPayment(@RequestBody CardDTO cardDTO, @PathVariable Integer customerId) throws Exception {
	    try {
	        paymentService.addCard(cardDTO, customerId);
	        return new ResponseEntity<>("Card added successfully.", HttpStatus.CREATED);
	    } catch (EPharmacyException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}

	public ResponseEntity<CardDTO> getCardDetails(String cardId) throws EPharmacyException {
		//write logic here
		return null;
	}
	
	public ResponseEntity<String> deleteCard(String cardId) throws EPharmacyException{
		//write logic here
		return null;
	}
}
