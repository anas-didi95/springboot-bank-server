package com.anasdidi.bank.domain.customer;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/cust")
@RequiredArgsConstructor
class CustomerController {

  private final CustomerService customerService;

  @PostMapping(value = { "", "/" })
  ResponseEntity<Customer> createCustomer(jakarta.servlet.http.HttpServletRequest request) {
    Customer responseBody = customerService.createCustomer();
    URI createdURI = URI.create(request.getRequestURI() + "/" + responseBody.getId());
    return ResponseEntity.created(createdURI).body(responseBody);
  }
}
