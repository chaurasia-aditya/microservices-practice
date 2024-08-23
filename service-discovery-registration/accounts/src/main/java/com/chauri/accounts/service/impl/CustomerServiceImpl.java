package com.chauri.accounts.service.impl;

import com.chauri.accounts.dto.AccountDto;
import com.chauri.accounts.dto.CustomerDetailsDto;
import com.chauri.accounts.dto.CustomerDto;
import com.chauri.accounts.entities.Account;
import com.chauri.accounts.entities.Customer;
import com.chauri.accounts.exception.ResourceNotFoundException;
import com.chauri.accounts.mapper.AccountMapper;
import com.chauri.accounts.mapper.CustomerMapper;
import com.chauri.accounts.repository.AccountRepository;
import com.chauri.accounts.repository.CustomerRepository;
import com.chauri.accounts.service.ICustomerService;
import com.chauri.accounts.service.client.CardsFeignClient;
import com.chauri.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     *
     * @param mobileNumber - Input from user
     * @return - Customer Details of user with given mobile number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerid", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        customerDetailsDto.setCardDto(cardsFeignClient.fetchCardDetails(mobileNumber).getBody());
        customerDetailsDto.setLoanDto(loansFeignClient.fetchLoanDetails(mobileNumber).getBody());

        return customerDetailsDto;
    }
}
