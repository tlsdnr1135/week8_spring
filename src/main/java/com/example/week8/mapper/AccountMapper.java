package com.example.week8.mapper;

import com.example.week8.dto.account.join.AccountReqDto;
import com.example.week8.entity.Adv;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    //AccountReqDto -> Adv
//    @Mapping(target = "name", expression = "java(accountReqDto.getName())")
//    @Mapping(target = "password", expression = "java(accountReqDto.getPassword())")
//    @Mapping(target = "role", expression = "java(accountReqDto.getRole())")
    @Mapping(target = "balance",constant = "1000000L")
    @Mapping(target = "adIngActYn",constant = "1")
    @Mapping(target = "eventMoneyBalance",constant = "0L")
    @Mapping(target = "dayLimitBudget",constant = "0L")
    @ValueMapping(source = "role",target = "role")
    Adv toAdv(AccountReqDto accountReqDto);

}
