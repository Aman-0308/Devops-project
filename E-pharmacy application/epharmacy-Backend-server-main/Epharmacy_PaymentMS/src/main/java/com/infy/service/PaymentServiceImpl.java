package com.infy.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infy.dto.CardDTO;
import com.infy.dto.PaymentDTO;
import com.infy.exception.EPharmacyException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${ADD_CARD_URL}")
    private String addCardUrl;

    @Value("${VIEW_CARDS_URL}")
    private String viewCardsUrl;

    @Value("${MAKE_PAYMENT_URL}")
    private String makePaymentUrl;

    @Value("${GET_PAYMENT_DETAILS_URL}")
    private String getPaymentDetailsUrl;

    @Value("${GET_CARD_DETAILS_URL}")
    private String getCardDetailsUrl;

    // Existing methods will use these URLs where applicable.

	@Override
	public List<CardDTO> viewCards(Integer customerId) throws EPharmacyException {
	    // Validate customerId
	    if (customerId == null || customerId <= 0) {
	        throw new EPharmacyException("Invalid customerId. It should be a positive integer.");
	    }

	    // Simulate fetching cards from the database
	    List<CardDTO> cards = cardRepository.findByCustomerId(customerId);

	    if (cards == null || cards.isEmpty()) {
	        throw new EPharmacyException("No cards found for the given customerId.");
	    }

	    return cards;
	}

	@Override
	public void addCard(CardDTO cardDTO) throws EPharmacyException, NoSuchAlgorithmException {
	    // Validate card details
	    if (cardDTO.getCardId().length() != 16 || !cardDTO.getCardId().matches("\\d{16}")) {
	        throw new EPharmacyException("Invalid card number. It should be a 16-digit number.");
	    }

	    if (!cardDTO.getCardType().equalsIgnoreCase("DEBIT") && !cardDTO.getCardType().equalsIgnoreCase("CREDIT")) {
	        throw new EPharmacyException("Invalid card type. It should be either DEBIT or CREDIT.");
	    }

	    if (cardDTO.getExpiryDate().isBefore(LocalDate.now())) {
	        throw new EPharmacyException("Expiry date should be a future date.");
	    }

	    if (!cardDTO.getNameOnCard().matches("[A-Za-z]+( [A-Za-z]+)*")) {
	        throw new EPharmacyException("Invalid name on card. It should contain only English letters with single spaces between words.");
	    }

	    if (cardDTO.getCvv().length() != 3 || !cardDTO.getCvv().matches("\\d{3}")) {
	        throw new EPharmacyException("Invalid CVV. It should be a 3-digit number.");
	    }

	    // Simulate saving card details
	    System.out.println("Card added successfully: " + cardDTO);
	}

	@Override
	public void deleteCard(String cardId) throws EPharmacyException {
	    // Validate cardId
	    if (cardId == null || cardId.isEmpty()) {
	        throw new EPharmacyException("Invalid cardId. It cannot be null or empty.");
	    }

	    // Simulate card deletion
	    boolean isDeleted = cardRepository.deleteByCardId(cardId);

	    if (!isDeleted) {
	        throw new EPharmacyException("Card not found or could not be deleted.");
	    }

	    System.out.println("Card deleted successfully: " + cardId);
	}

	@Override
	public Integer makePayment(CardDTO cardDTO, Float amountToPay)
			throws EPharmacyException, NoSuchAlgorithmException {
	    // Validate card details
	    if (cardDTO.getCvv().length() != 3 || !cardDTO.getCvv().matches("\\d{3}")) {
	        throw new EPharmacyException("Invalid CVV. It should be a 3-digit number.");
	    }

	    // Simulate card validation (e.g., checking CVV, expiry date, etc.)
	    if (cardDTO.getExpiryDate().isBefore(LocalDate.now())) {
	        throw new EPharmacyException("Card has expired.");
	    }

	    // Simulate payment processing
	    System.out.println("Processing payment of amount: " + amountToPay + " using card: " + cardDTO.getCardId());

	    // Return a dummy payment ID for now
	    return 12345;
	}

	@Override
	public PaymentDTO getPaymentDetails(Integer paymentId) throws EPharmacyException {
	    // Validate paymentId
	    if (paymentId == null || paymentId <= 0) {
	        throw new EPharmacyException("Invalid paymentId. It should be a positive integer.");
	    }

	    // Simulate fetching payment details
	    PaymentDTO payment = paymentRepository.findByPaymentId(paymentId);

	    if (payment == null) {
	        throw new EPharmacyException("No payment details found for the given paymentId.");
	    }

	    return payment;
	}

	@Override
	public CardDTO getCardDetails(String cardId) throws EPharmacyException {
	    // Validate cardId
	    if (cardId == null || cardId.isEmpty()) {
	        throw new EPharmacyException("Invalid cardId. It cannot be null or empty.");
	    }

	    // Simulate fetching card details
	    CardDTO card = cardRepository.findByCardId(cardId);

	    if (card == null) {
	        throw new EPharmacyException("No card details found for the given cardId.");
	    }

	    return card;
	}

}
