package com.anasdidi.bank.domain.currencyrate;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRateDTO {

  private LocalDate date;
  private String fromCurrency;
  private String toCurrency;
  private BigDecimal rate;
}
