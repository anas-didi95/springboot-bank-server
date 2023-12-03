package com.anasdidi.bank.exception;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AccountInsufficientBalanceException extends Exception {

  private final String accountNo;
  private final BigDecimal accountBalance;
  private final BigDecimal amount;
}
