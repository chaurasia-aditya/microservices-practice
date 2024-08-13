package com.chauri.loans.service.impl;

import com.chauri.loans.constants.LoanConstants;
import com.chauri.loans.dto.LoanDto;
import com.chauri.loans.entities.Loan;
import com.chauri.loans.exception.LoanAlreadyExistsException;
import com.chauri.loans.exception.ResourceNotFoundException;
import com.chauri.loans.mapper.LoanMapper;
import com.chauri.loans.repository.LoanRepository;
import com.chauri.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
    private LoanRepository loanRepository;

    /**
     * @param mobileNumber - User's mobile number
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()){
            throw new LoanAlreadyExistsException("Loan with given mobile number already registered "+ mobileNumber);
        }

        loanRepository.save(createNewLoan(mobileNumber));
    }

    /**
     *
     * @param mobileNumber - User's mobile number
     * @return new Loan object
     */
    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();

        long loanNumber = 100000000000L + new Random().nextInt(900000000);

        newLoan.setLoanNumber(Long.toString(loanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }

    /**
     *
     * @param mobileNumber - Input mobile number from user
     * @return - Loan details based on mobile number
     */
    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        return LoanMapper.mapToLoanDto(loan, new LoanDto());
    }

    /**
     *
     * @param loanDto - Loan Dto object
     * @return - boolean indicating success/failure of update operation
     */
    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loanDto.getLoanNumber())
        );

        LoanMapper.mapToLoan(loanDto, loan);
        loanRepository.save(loan);

        return true;
    }

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return - boolean indicating success/failure of delete operation
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        loanRepository.deleteById(loan.getLoanId());

        return true;
    }
}
