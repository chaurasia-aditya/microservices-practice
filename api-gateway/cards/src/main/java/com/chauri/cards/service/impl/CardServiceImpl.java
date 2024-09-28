package com.chauri.cards.service.impl;

import com.chauri.cards.constants.CardConstants;
import com.chauri.cards.dto.CardDto;
import com.chauri.cards.entities.Card;
import com.chauri.cards.exception.CardAlreadyExistsException;
import com.chauri.cards.exception.ResourceNotFoundException;
import com.chauri.cards.mapper.CardMapper;
import com.chauri.cards.repository.CardRepository;
import com.chauri.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {
    private CardRepository cardRepository;

    /**
     * @param mobileNumber - User's mobile number
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent()){
            throw new CardAlreadyExistsException("Card with given mobile number already registered "+ mobileNumber);
        }

        cardRepository.save(createNewCard(mobileNumber));
    }

    /**
     *
     * @param mobileNumber - User's mobile number
     * @return new Card object
     */
    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();

        long cardNumber = 100000000000L + new Random().nextInt(900000000);

        newCard.setCardNumber(Long.toString(cardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);

        return newCard;
    }

    /**
     *
     * @param mobileNumber - Input mobile number from user
     * @return - Card details based on mobile number
     */
    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        return CardMapper.mapToCardDto(card, new CardDto());
    }

    /**
     *
     * @param cardDto - Card Dto object
     * @return - boolean indicating success/failure of update operation
     */
    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardDto.getCardNumber())
        );

        CardMapper.mapToCard(cardDto, card);
        cardRepository.save(card);

        return true;
    }

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return - boolean indicating success/failure of delete operation
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        cardRepository.deleteById(card.getCardId());
        return true;
    }
}
