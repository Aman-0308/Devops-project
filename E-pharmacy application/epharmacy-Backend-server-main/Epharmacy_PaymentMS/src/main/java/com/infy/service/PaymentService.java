package com.infy.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.infy.dto.CardDTO;
import com.infy.dto.PaymentDTO;
import com.infy.exception.EPharmacyException;

public interface PaymentService {
	public Integer makePayment(CardDTO cardDTO,Float amountToPay)
			throws EPharmacyException, NoSuchAlgorithmException;

	public void addCard(CardDTO cardDTO) throws Exception;
	public void deleteCard(String cardId) throws EPharmacyException;
	public List<CardDTO> viewCards(Integer customerId) throws EPharmacyException;
	PaymentDTO getPaymentDetails(Integer paymentId) throws EPharmacyException;
	CardDTO getCardDetails(String cardId) throws EPharmacyException;
}
