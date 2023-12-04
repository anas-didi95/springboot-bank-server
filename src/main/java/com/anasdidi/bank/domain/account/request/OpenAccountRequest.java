package com.anasdidi.bank.domain.account.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OpenAccountRequest {

  private String customerNo;

  @NotBlank
  private String customerName;
}
