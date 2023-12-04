package com.anasdidi.bank.domain.account;

import java.net.URI;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anasdidi.bank.common.PaginationDTO;
import com.anasdidi.bank.domain.account.request.DepositAccountRequest;
import com.anasdidi.bank.domain.account.request.OpenAccountRequest;
import com.anasdidi.bank.domain.account.request.TransferAccountRequest;
import com.anasdidi.bank.domain.account.request.WithdrawAccountRequest;
import com.anasdidi.bank.exception.AccountInsufficientBalanceException;
import com.anasdidi.bank.exception.AccountNotFoundException;

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
      @Validated @RequestBody OpenAccountRequest requestBody) {
    AccountDTO responseBody = accountService.openAccount(requestBody);
    URI location = URI.create(request.getRequestURI() + "/" + responseBody.getId());
    return ResponseEntity.created(location).body(responseBody);
  }

  @GetMapping({ "", "/" })
  ResponseEntity<PaginationDTO<AccountDTO>> getAccountList(
      @RequestParam(required = false) String accountNo,
      @RequestParam(required = false) String customerNo,
      @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
    PaginationDTO<AccountDTO> responseBody = accountService.getAccountList(accountNo, customerNo, pageable);
    return ResponseEntity.ok().body(responseBody);
  }

  @GetMapping("/{accountId}")
  ResponseEntity<AccountDTO> getAccount(@PathVariable String accountId) throws AccountNotFoundException {
    AccountDTO responseBody = accountService.getAccount(accountId);
    return ResponseEntity.ok().body(responseBody);
  }

  @PutMapping("/deposit")
  ResponseEntity<AccountDTO> depositAccount(@Validated @RequestBody DepositAccountRequest requestBody)
      throws AccountNotFoundException {
    AccountDTO responseBody = accountService.depositAccount(requestBody);
    return ResponseEntity.ok().body(responseBody);
  }

  @PutMapping("/withdraw")
  ResponseEntity<AccountDTO> withdrawAccount(@Validated @RequestBody WithdrawAccountRequest requestBody)
      throws AccountNotFoundException, AccountInsufficientBalanceException {
    AccountDTO responseBody = accountService.withdrawAccount(requestBody);
    return ResponseEntity.ok().body(responseBody);
  }

  @PutMapping("/transfer")
  ResponseEntity<Map<String, AccountDTO>> transferAccount(@Validated @RequestBody TransferAccountRequest requestBody)
      throws AccountNotFoundException, AccountInsufficientBalanceException {
    Map<String, AccountDTO> responseBody = accountService.transferAccount(requestBody);
    return ResponseEntity.ok().body(responseBody);
  }
}
