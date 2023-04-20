package com.example.week8.mapper;

import com.example.week8.dto.adv.find.AdvFindResDto;
import com.example.week8.dto.adv.find.AdvFindResDto.AdvFindResDtoBuilder;
import com.example.week8.dto.adv.update.AdvAdIngActYnResDto;
import com.example.week8.dto.adv.update.AdvAdIngActYnResDto.AdvAdIngActYnResDtoBuilder;
import com.example.week8.dto.adv.update.AdvDayLimitBudgetResDto;
import com.example.week8.dto.adv.update.AdvDayLimitBudgetResDto.AdvDayLimitBudgetResDtoBuilder;
import com.example.week8.entity.Adv;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T09:29:39+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
public class AdvMapperImpl implements AdvMapper {

    @Override
    public AdvFindResDto toAdvFindResDto(Adv adv) {
        if ( adv == null ) {
            return null;
        }

        AdvFindResDtoBuilder advFindResDto = AdvFindResDto.builder();

        advFindResDto.adIngActYn( adv.getAdIngActYn() );
        advFindResDto.balance( adv.getBalance() );
        advFindResDto.eventMoneyBalance( adv.getEventMoneyBalance() );
        advFindResDto.dayLimitBudget( adv.getDayLimitBudget() );

        return advFindResDto.build();
    }

    @Override
    public AdvAdIngActYnResDto toAdvAdIngActYnResDto(Adv adv) {
        if ( adv == null ) {
            return null;
        }

        AdvAdIngActYnResDtoBuilder advAdIngActYnResDto = AdvAdIngActYnResDto.builder();

        advAdIngActYnResDto.name( adv.getName() );
        advAdIngActYnResDto.adIngActYn( adv.getAdIngActYn() );
        advAdIngActYnResDto.balance( adv.getBalance() );
        advAdIngActYnResDto.eventMoneyBalance( adv.getEventMoneyBalance() );
        advAdIngActYnResDto.dayLimitBudget( adv.getDayLimitBudget() );

        return advAdIngActYnResDto.build();
    }

    @Override
    public AdvDayLimitBudgetResDto toAdvDayLimitBudgetResDto(Adv adv) {
        if ( adv == null ) {
            return null;
        }

        AdvDayLimitBudgetResDtoBuilder advDayLimitBudgetResDto = AdvDayLimitBudgetResDto.builder();

        advDayLimitBudgetResDto.name( adv.getName() );
        advDayLimitBudgetResDto.dayLimitBudget( adv.getDayLimitBudget() );

        return advDayLimitBudgetResDto.build();
    }
}
