package com.chauri.loans.repository;

import com.chauri.loans.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByLoanNumber(String loanNumber);

    Optional<Loan> findByMobileNumber(String mobileNumber);
}
