package com.anasdidi.bank.domain.customer;

import com.anasdidi.bank.common.BaseDTO;

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
public class CustomerDTO extends BaseDTO {

  private String id;
  private String customerNo;
  private String name;
}
