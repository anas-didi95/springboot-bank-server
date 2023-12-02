package com.anasdidi.bank.common;

import java.time.LocalDateTime;

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
public class BaseDTO {

  private String createdBy;
  private LocalDateTime createdDate;
  private String updatedBy;
  private LocalDateTime updatedDate;
  private Integer version;
}
