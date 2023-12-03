package com.anasdidi.bank.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AccountNotFoundException extends Exception {

  private final String accountId;
  private final String accountNo;
}
