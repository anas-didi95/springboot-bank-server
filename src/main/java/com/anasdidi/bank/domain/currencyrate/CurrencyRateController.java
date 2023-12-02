package com.anasdidi.bank.domain.currencyrate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/ccy-rate")
@RequiredArgsConstructor
public class CurrencyRateController {

  private final CurrencyRateService currencyRateService;

  @GetMapping("/{fromCurrency}/{toCurrency}")
  ResponseEntity<CurrencyRateDTO> getCurrencyRate(
      @PathVariable String fromCurrency,
      @PathVariable String toCurrency) {
    CurrencyRateDTO responseBody = currencyRateService.getCurrencyRate(fromCurrency, toCurrency);
    return ResponseEntity.ok().body(responseBody);
  }
}
