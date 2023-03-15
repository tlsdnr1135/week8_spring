package com.example.week8.config;

import com.example.week8.dto.account.join.AccountReqDto;
import com.example.week8.entity.Adv;
import com.example.week8.enums.AccountRoleEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    //AccountReqDto -> Adv
    @Mapping(target = "name", expression = "java(accountReqDto.getName())")
    @Mapping(target = "password", expression = "java(accountReqDto.getPassword())")
//    @Mapping(target = "role", expression = "java(accountReqDto.getRole())")
    @Mapping(target = "balance",constant = "1000000L")
    @ValueMapping(source = "role",target = "role")
    Adv toAdv(AccountReqDto accountReqDto);

}
