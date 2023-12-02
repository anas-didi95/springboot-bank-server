package com.anasdidi.bank.domain.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anasdidi.bank.common.PaginationDTO;
import com.anasdidi.bank.domain.account.request.DepositAccountRequest;
import com.anasdidi.bank.domain.account.request.OpenAccountRequest;
import com.anasdidi.bank.domain.account.request.WithdrawAccountRequest;
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

  public PaginationDTO<AccountDTO> getAccountList(String accountNo, String customerNo, Pageable pageable) {
    boolean hasAccountNo = StringUtils.isNotBlank(accountNo);
    boolean hasCustomerNo = StringUtils.isNotBlank(customerNo);

    Page<Account> page;
    if (hasAccountNo && hasCustomerNo) {
      page = accountRepository.findAllByAccountNoAndCustomerNo(accountNo, customerNo, pageable);
    } else if (hasAccountNo) {
      page = accountRepository.findAllByAccountNo(accountNo, pageable);
    } else if (hasCustomerNo) {
      page = accountRepository.findAllByCustomerNo(customerNo, pageable);
    } else {
      page = accountRepository.findAll(pageable);
    }

    List<AccountDTO> resultList = page.getContent().stream()
        .map(AccountMapper.INSTANCE::toDTO)
        .toList();
    return new PaginationDTO<>(resultList, page);
  }

  public AccountDTO getAccount(String accountId) {
    Optional<Account> result = accountRepository.findById(accountId);
    if (result.isEmpty()) {
      return null;
    }
    return AccountMapper.INSTANCE.toDTO(result.get());
  }

  public AccountDTO depositAccount(DepositAccountRequest request) {
    Optional<Account> result = accountRepository.findByAccountNo(request.getAccountNo());
    if (result.isEmpty()) {
      return null;
    }

    Account entity = result.get();
    entity.setAccountBalance(entity.getAccountBalance().add(request.getAmount()));
    entity = accountRepository.saveAndFlush(entity);
    return AccountMapper.INSTANCE.toDTO(entity);
  }

  public AccountDTO withdrawAccount(WithdrawAccountRequest request) {
    Optional<Account> result = accountRepository.findByAccountNo(request.getAccountNo());
    if (result.isEmpty()) {
      return null;
    }

    Account entity = result.get();
    entity.setAccountBalance(entity.getAccountBalance().subtract(request.getAmount()));
    entity = accountRepository.saveAndFlush(entity);
    return AccountMapper.INSTANCE.toDTO(entity);
  }
}
