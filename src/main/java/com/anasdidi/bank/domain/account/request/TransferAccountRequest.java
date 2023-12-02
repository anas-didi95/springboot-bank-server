package com.anasdidi.bank.domain.account.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferAccountRequest {

  private String fromAccountNo;
  private String toAccountNo;
  private BigDecimal amount;
}
