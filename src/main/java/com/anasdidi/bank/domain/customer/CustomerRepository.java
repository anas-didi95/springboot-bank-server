package com.anasdidi.bank.domain.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface CustomerRepository extends JpaRepository<Customer, String> {

  Page<Customer> findAllByCustomerNoAndNameContains(String customerNo, String name, Pageable pageable);

  Page<Customer> findAllByCustomerNo(String customerNo, Pageable pageable);

  Page<Customer> findAllByNameContains(String name, Pageable pageable);
}
