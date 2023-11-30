package com.anasdidi.bank.domain.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acct")
public class AccountController {

  @GetMapping("")
  public ResponseEntity<String> helloWorld() {
    return ResponseEntity.ok("OK!");
  }
}
