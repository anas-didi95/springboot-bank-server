package com.anasdidi.bank.common;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {

  private String code;
  private String message;
  private List<Map<String, String>> errorList;
}
