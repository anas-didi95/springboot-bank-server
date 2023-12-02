package com.anasdidi.bank.domain.account;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  AccountDTO toDTO(Account entity);

  Account toEntity(AccountDTO dto);
}
