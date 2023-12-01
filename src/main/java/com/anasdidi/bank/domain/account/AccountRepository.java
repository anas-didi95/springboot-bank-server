package com.anasdidi.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<Account, String> {
}
