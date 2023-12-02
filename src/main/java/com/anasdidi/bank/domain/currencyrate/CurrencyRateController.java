package com.anasdidi.bank.domain.currencyrate;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/ccy-rate")
@RequiredArgsConstructor
public class CurrencyRateController {

  private final CurrencyRateService currencyRateService;

  @GetMapping("/{fromCurrency}/{toCurrency}")
  ResponseEntity<List<CurrencyRateDTO>> getCurrencyRateList(
      @PathVariable String fromCurrency,
      @PathVariable String toCurrency,
      @RequestParam(required = false, name = "date") List<LocalDate> dateList) {
    List<CurrencyRateDTO> responseBody = currencyRateService.getCurrencyRateList(fromCurrency, toCurrency, dateList);
    return ResponseEntity.ok().body(responseBody);
  }
}
