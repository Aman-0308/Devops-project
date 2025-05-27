package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
    // Find all cards by customer ID
    List<Card> findByCustomerId(Integer customerId);

    // Find a card by card ID
    Card findByCardId(String cardId);

    // Delete a card by card ID
    void deleteByCardId(String cardId);
}
