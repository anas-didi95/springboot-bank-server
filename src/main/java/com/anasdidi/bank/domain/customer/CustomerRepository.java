package com.anasdidi.bank.domain.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

  Page<Customer> findAllByCustomerNoAndNameContains(String customerNo, String name, Pageable pageable);

  Page<Customer> findAllByCustomerNo(String customerNo, Pageable pageable);

  Page<Customer> findAllByNameContains(String name, Pageable pageable);

  Optional<Customer> findByCustomerNo(String customerNo);
}
