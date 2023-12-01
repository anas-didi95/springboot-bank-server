package com.anasdidi.bank.common;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;

@Getter
public class PaginationDTO<T> {

  public PaginationDTO(List<T> resultList, Page<?> page) {
    this.resultList = resultList;
    this.totalElements = page.getTotalElements();
    this.totalPages = page.getTotalPages();
    this.number = page.getNumber();
    this.numberOfElements = page.getNumberOfElements();
    this.size = page.getSize();
  }

  private List<T> resultList;
  private long totalElements;
  private int totalPages;
  private int number;
  private int numberOfElements;
  private int size;
}
