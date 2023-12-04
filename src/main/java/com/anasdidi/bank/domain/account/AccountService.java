package com.anasdidi.bank.domain.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anasdidi.bank.common.PaginationDTO;
import com.anasdidi.bank.domain.account.request.DepositAccountRequest;
import com.anasdidi.bank.domain.account.request.OpenAccountRequest;
import com.anasdidi.bank.domain.account.request.TransferAccountRequest;
import com.anasdidi.bank.domain.account.request.WithdrawAccountRequest;
import com.anasdidi.bank.domain.customer.Customer;
import com.anasdidi.bank.domain.customer.CustomerRepository;
import com.anasdidi.bank.domain.customer.CustomerUtils;
import com.anasdidi.bank.exception.AccountInsufficientBalanceException;
import com.anasdidi.bank.exception.AccountNotFoundException;

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
          .customerNo(CustomerUtils.generateCustomerNo())
          .name(request.getCustomerName())
          .build();
    } else {
      customer = result.get();
    }

    Account entity = Account.builder()
        .customer(customer)
        .accountNo(AccountUtils.generateAccountNo())
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

  public AccountDTO getAccount(String accountId) throws AccountNotFoundException {
    Optional<Account> result = accountRepository.findById(accountId);
    if (result.isEmpty()) {
      throw AccountNotFoundException.builder().accountId(accountId).build();
    }
    return AccountMapper.INSTANCE.toDTO(result.get());
  }

  public AccountDTO depositAccount(DepositAccountRequest request) throws AccountNotFoundException {
    Optional<Account> result = accountRepository.findByAccountNo(request.getAccountNo());
    if (result.isEmpty()) {
      throw AccountNotFoundException.builder().accountNo(request.getAccountNo()).build();
    }

    Account entity = result.get();
    entity.setAccountBalance(entity.getAccountBalance().add(request.getAmount()));
    entity = accountRepository.saveAndFlush(entity);
    return AccountMapper.INSTANCE.toDTO(entity);
  }

  public AccountDTO withdrawAccount(WithdrawAccountRequest request)
      throws AccountNotFoundException, AccountInsufficientBalanceException {
    Optional<Account> result = accountRepository.findByAccountNo(request.getAccountNo());
    if (result.isEmpty()) {
      throw AccountNotFoundException.builder().accountNo(request.getAccountNo()).build();
    }

    Account entity = result.get();
    if (entity.getAccountBalance().compareTo(request.getAmount()) < 0) {
      throw AccountInsufficientBalanceException.builder().accountNo(entity.getAccountNo())
          .accountBalance(entity.getAccountBalance()).amount(request.getAmount()).build();
    }
    entity.setAccountBalance(entity.getAccountBalance().subtract(request.getAmount()));
    entity = accountRepository.saveAndFlush(entity);
    return AccountMapper.INSTANCE.toDTO(entity);
  }

  public Map<String, AccountDTO> transferAccount(TransferAccountRequest request)
      throws AccountNotFoundException, AccountInsufficientBalanceException {
    Optional<Account> fromResult = accountRepository.findByAccountNo(request.getFromAccountNo());
    if (fromResult.isEmpty()) {
      throw AccountNotFoundException.builder().accountNo(request.getFromAccountNo()).build();
    }
    Optional<Account> toResult = accountRepository.findByAccountNo(request.getToAccountNo());
    if (toResult.isEmpty()) {
      throw AccountNotFoundException.builder().accountNo(request.getToAccountNo()).build();
    }

    List<Account> saveList = new ArrayList<>();
    Account fromEntity = fromResult.get();
    if (fromEntity.getAccountBalance().compareTo(request.getAmount()) < 0) {
      throw AccountInsufficientBalanceException.builder().accountNo(fromEntity.getAccountNo())
          .accountBalance(fromEntity.getAccountBalance()).amount(request.getAmount()).build();
    }
    fromEntity.setAccountBalance(fromEntity.getAccountBalance().subtract(request.getAmount()));
    saveList.add(fromEntity);

    Account toEntity = toResult.get();
    toEntity.setAccountBalance(toEntity.getAccountBalance().add(request.getAmount()));
    saveList.add(toEntity);

    saveList = accountRepository.saveAllAndFlush(saveList);
    return Map.of(
        "fromAccount", AccountMapper.INSTANCE.toDTO(saveList.get(0)),
        "toAccount", AccountMapper.INSTANCE.toDTO(saveList.get(1)));
  }
}
