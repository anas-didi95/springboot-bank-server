package com.anasdidi.bank.domain.customer;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public Customer createCustomer() {
    Customer customer = Customer.builder()
        .customerNo("0123456789")
        .name("ANAS JUWAIDI")
        .build();
    customer = customerRepository.saveAndFlush(customer);
    return customer;
  }
}
