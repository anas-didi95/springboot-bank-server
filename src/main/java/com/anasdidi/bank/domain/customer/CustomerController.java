package com.anasdidi.bank.domain.customer;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/cust")
@RequiredArgsConstructor
class CustomerController {

  private final CustomerService customerService;

  @PostMapping(value = { "", "/" })
  ResponseEntity<CustomerDTO> createCustomer(HttpServletRequest request,
      @RequestBody CustomerDTO requestBody) {
    CustomerDTO responseBody = customerService.createCustomer(requestBody);
    URI createdURI = URI.create(request.getRequestURI() + "/" + responseBody.getId());
    return ResponseEntity.created(createdURI).body(responseBody);
  }

  @GetMapping(value = { "", "/" })
  ResponseEntity<List<CustomerDTO>> getCustomerList() {
    List<CustomerDTO> responseBody = customerService.getCustomerList();
    return ResponseEntity.ok().body(responseBody);
  }
}
