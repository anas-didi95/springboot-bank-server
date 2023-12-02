package com.anasdidi.bank.domain.currencyrate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyRateService {

  @SuppressWarnings("unchecked")
  public CurrencyRateDTO getCurrencyRate(String fromCurrency, String toCurrency) {
    String uri = String.format(
        "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/%s/%s.min.json", fromCurrency,
        toCurrency);
    Map<String, Object> responseBody = WebClient.create()
        .get()
        .uri(URI.create(uri))
        .retrieve()
        .bodyToMono(Map.class)
        .block();

    return CurrencyRateDTO.builder()
        .fromCurrency(fromCurrency)
        .toCurrency(toCurrency)
        .date(LocalDate.parse((String) responseBody.get("date")))
        .rate(BigDecimal.valueOf((Double) responseBody.get(toCurrency)))
        .build();
  }
}
