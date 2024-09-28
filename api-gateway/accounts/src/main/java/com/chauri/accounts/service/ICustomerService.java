package com.chauri.accounts.service;

import com.chauri.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    /**
     *
     * @param mobileNumber - Input from user
     * @return - Customer Details of user with given mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
