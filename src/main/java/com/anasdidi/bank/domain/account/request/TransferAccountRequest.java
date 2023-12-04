package com.anasdidi.bank.domain.account.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferAccountRequest {

  @NotBlank
  private String fromAccountNo;

  @NotBlank
  private String toAccountNo;

  @NotNull
  @Positive
  private BigDecimal amount;
}
