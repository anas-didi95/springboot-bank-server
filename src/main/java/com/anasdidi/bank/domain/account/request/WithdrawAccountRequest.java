package com.anasdidi.bank.domain.account.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class WithdrawAccountRequest {

  private String accountNo;
  private BigDecimal amount;
}
