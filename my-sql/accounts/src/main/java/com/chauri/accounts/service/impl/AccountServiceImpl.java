package com.chauri.accounts.service.impl;

import com.chauri.accounts.constants.AccountConstants;
import com.chauri.accounts.dto.AccountDto;
import com.chauri.accounts.dto.CustomerDto;
import com.chauri.accounts.entities.Account;
import com.chauri.accounts.entities.Customer;
import com.chauri.accounts.exception.CustomerExistsException;
import com.chauri.accounts.exception.ResourceNotFoundException;
import com.chauri.accounts.mapper.AccountMapper;
import com.chauri.accounts.mapper.CustomerMapper;
import com.chauri.accounts.repository.AccountRepository;
import com.chauri.accounts.repository.CustomerRepository;
import com.chauri.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto - CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerExistsException("Customer with given mobile number already registered "+customerDto.getMobileNumber());
        }

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    /**
     *
     * @param customer - Customer Object
     * @return new account details
     */
    private Account createNewAccount(Customer customer){
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long generatedAccountNumber = 1000000000L+new Random().nextInt(900000000);

        newAccount.setAccountNumber(generatedAccountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");

        return newAccount;
    }

    /**
     *
     * @param mobileNumber - Input mobile number from user
     * @return - Customer detail
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerid", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        return customerDto;
    }

    /**
     *
     * @param customerDto - Customer Dto object
     * @return
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        AccountDto accountDto = customerDto.getAccountDto();

        if(accountDto !=null){
            Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "Account Number", accountDto.getAccountNumber().toString())
            );

            AccountMapper.mapToAccount(accountDto, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);

            return true;
        }
        return false;
    }

    /**
     *
     * @param mobileNumber - Input mobile number
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);

        return true;
    }
}
