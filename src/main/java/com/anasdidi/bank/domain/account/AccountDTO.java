package com.anasdidi.bank.domain.account;

import java.math.BigDecimal;

import com.anasdidi.bank.common.BaseDTO;
import com.anasdidi.bank.domain.customer.CustomerDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class AccountDTO extends BaseDTO {

  private String id;
  private CustomerDTO customer;
  private String accountNo;
  private BigDecimal accountBalance;
}
