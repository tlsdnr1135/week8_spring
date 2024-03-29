package com.example.week8.service;

import com.example.week8.enums.AccountRoleEnum;
import com.example.week8.mapper.AccountMapper;
import com.example.week8.dto.account.join.AccountReqDto;
import com.example.week8.dto.account.join.AccountResDto;
import com.example.week8.entity.Admin;
import com.example.week8.entity.Adv;
import com.example.week8.repository.AdminRepository;
import com.example.week8.repository.AdvRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AdvRepository advRepository;
    private final AdminRepository adminRepository;

    //회원가입
    public String accountJoin(AccountReqDto accountReqDto){
        System.out.println("Controller accountService안");

        String member;
        if(accountReqDto.getRole().equals(AccountRoleEnum.ROLE_ADV.getAccountRole())){ //관리자광고주
            System.out.println("광고주 if문 안");
            Adv adv = AccountMapper.INSTANCE.toAdv(accountReqDto);
            adv.encodePassword(passwordEncoder);
            advRepository.save(adv);
            member = adv.getName();
        }else{ //관리자
            System.out.println("관리자 if문 안");
            Admin admin = accountReqDto.toAdmin();
            admin.encodePassword(passwordEncoder);
            adminRepository.save(admin);
            member = admin.getName();
        }

        return new AccountResDto().toResDto(member);
    }

}
