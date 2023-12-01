package com.anasdidi.bank.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;

interface CustomerRepository extends JpaRepository<Customer, String> {
}
