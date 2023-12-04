package com.anasdidi.bank.domain.customer;

import com.anasdidi.bank.common.BaseDTO;

import jakarta.validation.constraints.NotBlank;
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

  @NotBlank
  private String customerNo;

  @NotBlank
  private String name;
}
