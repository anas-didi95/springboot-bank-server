package com.anasdidi.bank.domain.customer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anasdidi.bank.common.PaginationDTO;

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

  public PaginationDTO<CustomerDTO> getCustomerList(Pageable pageable) {
    Page<Customer> page = customerRepository.findAll(pageable);
    List<CustomerDTO> resultList = page.getContent().stream()
        .map(CustomerMapper.INSTANCE::toDTO)
        .toList();
    return new PaginationDTO<CustomerDTO>(resultList, page);
  }
}
