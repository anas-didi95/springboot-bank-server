package com.anasdidi.bank.domain.customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  CustomerDTO toDTO(Customer entity);

  Customer toEntity(CustomerDTO dto);
}
