package com.chauri.cards.service;

import com.chauri.cards.dto.CardDto;

public interface ICardService {
    /**
     * @param mobileNumber - User's mobile number
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - User's mobile number
     * @return - Card Details of user
     */
    CardDto fetchCard(String mobileNumber);

    /**
     *
     * @param cardDto - Card Dto object
     * @return boolean indication success/failure of update operation
     */
    boolean updateCard(CardDto cardDto);

    /**
     *
     * @param mobileNumber - User's mobile number
     * @return boolean indicating success/failure of delete operation
     */
    boolean deleteCard(String mobileNumber);
}
