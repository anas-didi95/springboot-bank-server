package com.anasdidi.bank.domain.customer;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anasdidi.bank.common.PaginationDTO;
import com.anasdidi.bank.exception.CustomerNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerDTO createCustomer(CustomerDTO dto) {
    Customer customer = CustomerMapper.INSTANCE.toEntity(dto);
    customer = customerRepository.saveAndFlush(customer);
    return CustomerMapper.INSTANCE.toDTO(customer);
  }

  public PaginationDTO<CustomerDTO> getCustomerList(String customerNo, String name, Pageable pageable) {
    boolean hasCustomerNo = StringUtils.isNotBlank(customerNo);
    boolean hasName = StringUtils.isNotBlank(name);

    Page<Customer> page;
    if (hasCustomerNo && hasName) {
      page = customerRepository.findAllByCustomerNoAndNameContains(customerNo, name, pageable);
    } else if (hasCustomerNo) {
      page = customerRepository.findAllByCustomerNo(customerNo, pageable);
    } else if (hasName) {
      page = customerRepository.findAllByNameContains(name, pageable);
    } else {
      page = customerRepository.findAll(pageable);
    }

    List<CustomerDTO> resultList = page.getContent().stream()
        .map(CustomerMapper.INSTANCE::toDTO)
        .toList();
    return new PaginationDTO<CustomerDTO>(resultList, page);
  }

  public CustomerDTO getCustomer(String customerId) throws CustomerNotFoundException {
    Optional<Customer> result = customerRepository.findById(customerId);
    if (result.isEmpty()) {
      throw CustomerNotFoundException.builder().customerId(customerId).build();
    }
    return CustomerMapper.INSTANCE.toDTO(result.get());
  }

  public CustomerDTO updateCustomer(String customerId, CustomerDTO newDTO) throws CustomerNotFoundException {
    Optional<Customer> result = customerRepository.findById(customerId);
    if (result.isEmpty()) {
      throw CustomerNotFoundException.builder().customerId(customerId).build();
    }

    Customer entity = result.get();
    entity.setName(newDTO.getName());
    entity = customerRepository.saveAndFlush(entity);
    return CustomerMapper.INSTANCE.toDTO(entity);
  }

  public void deleteCustomer(String customerId) throws CustomerNotFoundException {
    Optional<Customer> result = customerRepository.findById(customerId);
    if (result.isEmpty()) {
      throw CustomerNotFoundException.builder().customerId(customerId).build();
    }
    customerRepository.delete(result.get());
  }
}
