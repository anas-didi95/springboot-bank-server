package com.anasdidi.bank.domain.account.request;

import lombok.Data;

@Data
public class OpenAccountRequest {

  private String customerNo;
  private String customerName;
  private String accountCurrency;
}
