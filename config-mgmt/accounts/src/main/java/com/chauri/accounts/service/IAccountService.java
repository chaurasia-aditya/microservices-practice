package com.chauri.accounts.service;

import com.chauri.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     *
     * @param customerDto - CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input from user
     * @return - Account Details of user
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto - Customer Dto object
     * @return boolean indication success/failure of update operation
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return boolean indication success/failure of delete operation
     */
    boolean deleteAccount(String mobileNumber);
}
