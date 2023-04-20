package com.example.week8.mapper;

import com.example.week8.dto.account.join.AccountReqDto;
import com.example.week8.entity.Adv;
import com.example.week8.entity.Adv.AdvBuilder;
import com.example.week8.enums.AccountRoleEnum;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T09:29:39+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Adv toAdv(AccountReqDto accountReqDto) {
        if ( accountReqDto == null ) {
            return null;
        }

        AdvBuilder adv = Adv.builder();

        adv.name( accountReqDto.getName() );
        adv.password( accountReqDto.getPassword() );
        if ( accountReqDto.getRole() != null ) {
            adv.role( Enum.valueOf( AccountRoleEnum.class, accountReqDto.getRole() ) );
        }

        adv.balance( (long) 1000000L );
        adv.adIngActYn( 1 );
        adv.eventMoneyBalance( (long) 0L );
        adv.dayLimitBudget( (long) 0L );

        return adv.build();
    }
}
