package com.anasdidi.bank.domain.account.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DepositAccountRequest {

  @NotBlank
  private String accountNo;

  @NotNull
  @Positive
  private BigDecimal amount;
}
