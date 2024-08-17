package com.chauri.loans.service;

import com.chauri.loans.dto.LoanDto;

public interface ILoanService {
    /**
     * @param mobileNumber - User's mobile number
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber - User's mobile number
     * @return - Loan Details of user
     */
    LoanDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loanDto - Loan Dto object
     * @return boolean indication success/failure of update operation
     */
    boolean updateLoan(LoanDto loanDto);

    /**
     *
     * @param mobileNumber - User's mobile number
     * @return boolean indicating success/failure of delete operation
     */
    boolean deleteLoan(String mobileNumber);
}
