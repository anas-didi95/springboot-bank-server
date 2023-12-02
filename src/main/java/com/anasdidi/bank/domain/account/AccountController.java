package com.anasdidi.bank.domain.account;

import java.net.URI;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anasdidi.bank.common.PaginationDTO;
import com.anasdidi.bank.domain.account.request.OpenAccountRequest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/acct")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping({ "", "/" })
  ResponseEntity<AccountDTO> openAccount(
      HttpServletRequest request,
      @RequestBody OpenAccountRequest requestBody) {
    AccountDTO responseBody = accountService.openAccount(requestBody);
    URI location = URI.create(request.getRequestURI() + "/" + responseBody.getId());
    return ResponseEntity.created(location).body(responseBody);
  }

  @GetMapping({ "", "/" })
  ResponseEntity<PaginationDTO<AccountDTO>> getAccountList(
      @RequestParam(required = false) String accountNo,
      @RequestParam(required = false) String customerNo,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {
    PaginationDTO<AccountDTO> responseBody = accountService.getAccountList(accountNo, customerNo, pageable);
    return ResponseEntity.ok().body(responseBody);
  }
}
