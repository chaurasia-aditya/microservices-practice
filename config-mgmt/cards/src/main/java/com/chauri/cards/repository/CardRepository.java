package com.chauri.cards.repository;

import com.chauri.cards.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardNumber(String cardNumber);

    Optional<Card> findByMobileNumber(String mobileNumber);
}
