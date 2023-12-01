package com.anasdidi.bank.domain.account;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anasdidi.bank.domain.account.request.OpenAccountRequest;
import com.anasdidi.bank.domain.customer.Customer;
import com.anasdidi.bank.domain.customer.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class AccountService {

  private final CustomerRepository customerRepository;
  private final AccountRepository accountRepository;

  public AccountDTO openAccount(OpenAccountRequest request) {
    Optional<Customer> result = customerRepository.findByCustomerNo(request.getCustomerNo());
    Customer customer;
    if (result.isEmpty()) {
      customer = Customer.builder()
          .customerNo(request.getCustomerNo())
          .name(request.getCustomerName())
          .build();
    } else {
      customer = result.get();
    }

    Account entity = Account.builder()
        .customer(customer)
        .accountNo(AccountUtils.generateAccountNo())
        .accountCurrency(request.getAccountCurrency())
        .accountBalance(BigDecimal.ZERO)
        .build();
    entity = accountRepository.saveAndFlush(entity);
    return AccountMapper.INSTANCE.toDTO(entity);
  }
}
