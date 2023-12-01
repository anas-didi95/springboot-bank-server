package com.anasdidi.bank.domain.customer;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerDTO createCustomer(CustomerDTO dto) {
    Customer customer = CustomerMapper.INSTANCE.toEntity(dto);
    customer = customerRepository.saveAndFlush(customer);
    return CustomerMapper.INSTANCE.toDTO(customer);
  }

  public List<CustomerDTO> getCustomerList() {
    List<Customer> resultList = customerRepository.findAll();
    return resultList.stream()
        .map(CustomerMapper.INSTANCE::toDTO)
        .toList();
  }
}
