package com.anasdidi.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.anasdidi.bank.common.ErrorDTO;
import com.anasdidi.bank.exception.AccountInsufficientBalanceException;
import com.anasdidi.bank.exception.AccountNotFoundException;
import com.anasdidi.bank.exception.CustomerNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestControllerAdvice {

  @ExceptionHandler(AccountInsufficientBalanceException.class)
  ResponseEntity<ErrorDTO> handleAccountInsufficientBalanceException(AccountInsufficientBalanceException ex) {
    String code = "E003";
    String message = "Account Insufficient Balance!";

    log.error("[handleAccountInsufficientBalanceException] code={}, message={}", code, message);
    log.error("[handleAccountInsufficientBalanceException] {}", ex.toString());

    return ResponseEntity.badRequest().body(ErrorDTO.builder().code(code).message(message).build());
  }

  @ExceptionHandler(AccountNotFoundException.class)
  ResponseEntity<ErrorDTO> handleAccountNotFoundException(AccountNotFoundException ex) {
    String code = "E002";
    String message = "Account Not Found!";

    log.error("[handleAccountNotFoundException] code={}, message={}", code, message);
    log.error("[handleAccountNotFoundException] {}", ex.toString());

    return ResponseEntity.badRequest().body(ErrorDTO.builder().code(code).message(message).build());
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  ResponseEntity<ErrorDTO> handleCustomerNotFoundException(CustomerNotFoundException ex) {
    String code = "E001";
    String message = "Customer Not Found!";

    log.error("[handleCustomerNotFoundException] code={}, message={}", code, message);
    log.error("[handleCustomerNotFoundException] {}", ex.toString());

    return ResponseEntity.badRequest().body(ErrorDTO.builder().code(code).message(message).build());
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorDTO> handleException(Exception ex) {
    String code = "E000";
    String message = "Request failed!";

    log.error("[handleException] code={}, message={}", code, message);
    log.error("[handleException] {}", ex.toString());

    return ResponseEntity.badRequest().body(ErrorDTO.builder().code(code).message(message).build());
  }
}
