package com.anasdidi.bank.domain.currencyrate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyRateService {

  @SuppressWarnings({ "rawtypes" })
  public List<CurrencyRateDTO> getCurrencyRateList(String fromCurrency, String toCurrency, List<LocalDate> dateList) {
    if (dateList == null) {
      dateList = new ArrayList<>();
    }
    if (dateList.isEmpty()) {
      dateList.add(LocalDate.now());
    }

    List<CompletableFuture<Map>> callList = new ArrayList<>();
    for (LocalDate date : dateList) {
      String uri = String.format(
          "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/%s/currencies/%s/%s.min.json",
          DateTimeFormatter.ISO_DATE.format(date),
          fromCurrency,
          toCurrency);
      callList.add(WebClient.create()
          .get()
          .uri(URI.create(uri))
          .retrieve()
          .bodyToMono(Map.class)
          .toFuture());
    }

    CompletableFuture<Void> callAll = CompletableFuture.allOf(callList.toArray(new CompletableFuture[0]));
    callAll.join();

    return callList.stream()
        .map(CompletableFuture::join)
        .map(m -> CurrencyRateDTO.builder()
            .fromCurrency(fromCurrency)
            .toCurrency(toCurrency)
            .date(LocalDate.parse((String) m.get("date")))
            .rate(BigDecimal.valueOf((Double) m.get(toCurrency)))
            .build())
        .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
        .toList();
  }
}
