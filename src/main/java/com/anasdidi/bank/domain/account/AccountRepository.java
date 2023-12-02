package com.anasdidi.bank.domain.account;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface AccountRepository extends JpaRepository<Account, String> {

  @Query(value = """
      select a.*
      from t_acct a
      inner join t_cust b on b.id = a.cust_id
      where a.acct_no = :accountNo
       and b.cust_no = :customerNo
      """, countQuery = """
      select a.*, b.cust_no
      from t_acct a
      inner join t_cust b on b.id = a.cust_id
      where a.acct_no = :accountNo
       and b.cust_no = :customerNo
      """, nativeQuery = true)
  Page<Account> findAllByAccountNoAndCustomerNo(String accountNo, String customerNo, Pageable pageable);

  Page<Account> findAllByAccountNo(String accountNo, Pageable pageable);

  @Query(value = """
      select a.*
      from t_acct a
      inner join t_cust b on b.id = a.cust_id
      where b.cust_no = :customerNo
      """, countQuery = """
      select count(1)
      from t_acct a
      inner join t_cust b on b.id = a.cust_id
      where b.cust_no = :customerNo
      """, nativeQuery = true)
  Page<Account> findAllByCustomerNo(String customerNo, Pageable pageable);

  Optional<Account> findByAccountNo(String accountNo);
}
