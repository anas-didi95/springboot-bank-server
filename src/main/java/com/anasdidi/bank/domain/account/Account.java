package com.anasdidi.bank.domain.account;

import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

import com.anasdidi.bank.common.BaseEntity;
import com.anasdidi.bank.domain.customer.Customer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "t_acct")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class Account extends BaseEntity {

  @Id
  @GenericGenerator(name = "generator", strategy = "guid")
  @GeneratedValue(generator = "generator")
  @Column(name = "id", nullable = false)
  private String id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "cust_id", referencedColumnName = "id", nullable = false)
  private Customer customer;

  @Column(name = "acct_no", length = 16, nullable = false)
  private String accountNo;

  @Column(name = "acct_bal", precision = 23, scale = 3, nullable = false)
  private BigDecimal accountBalance;
}
