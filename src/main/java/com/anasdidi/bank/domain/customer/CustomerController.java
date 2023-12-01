package com.anasdidi.bank.domain.customer;

import java.net.URI;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anasdidi.bank.common.PaginationDTO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/cust")
@RequiredArgsConstructor
class CustomerController {

  private final CustomerService customerService;

  @PostMapping({ "", "/" })
  ResponseEntity<CustomerDTO> createCustomer(
      HttpServletRequest request,
      @RequestBody CustomerDTO requestBody) {
    CustomerDTO responseBody = customerService.createCustomer(requestBody);
    URI createdURI = URI.create(request.getRequestURI() + "/" + responseBody.getId());
    return ResponseEntity.created(createdURI).body(responseBody);
  }

  @GetMapping({ "", "/" })
  ResponseEntity<PaginationDTO<CustomerDTO>> getCustomerList(
      @RequestParam(required = false) String customerNo,
      @RequestParam(required = false) String name,
      @PageableDefault(page = 0, size = 10) Pageable pageable) {
    PaginationDTO<CustomerDTO> responseBody = customerService.getCustomerList(customerNo, name, pageable);
    return ResponseEntity.ok().body(responseBody);
  }

  @GetMapping("/{customerId}")
  ResponseEntity<CustomerDTO> getCustomer(@PathVariable String customerId) {
    CustomerDTO responseBody = customerService.getCustomer(customerId);
    return ResponseEntity.ok().body(responseBody);
  }

  @PutMapping("/{customerId}")
  ResponseEntity<CustomerDTO> updateCustomer(
      @PathVariable String customerId,
      @RequestBody CustomerDTO requestBody) {
    CustomerDTO responseBody = customerService.updateCustomer(customerId, requestBody);
    return ResponseEntity.ok().body(responseBody);
  }

  @DeleteMapping("/{customerId}")
  ResponseEntity<Void> deleteCustomer(@PathVariable String customerId) {
    customerService.deleteCustomer(customerId);
    return ResponseEntity.noContent().build();
  }
}