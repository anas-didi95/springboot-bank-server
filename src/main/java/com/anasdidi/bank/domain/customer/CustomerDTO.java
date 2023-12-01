package com.anasdidi.bank.domain.customer;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {

  private String id;
  private String customerNo;
  private String name;
  private String createdBy;
  private LocalDateTime createdDate;
  private String updatedBy;
  private LocalDateTime updatedDate;
  private Integer version;
}
