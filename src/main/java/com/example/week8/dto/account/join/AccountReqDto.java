package com.example.week8.dto.account.join;

import com.example.week8.entity.Account;
import com.example.week8.entity.Admin;
import com.example.week8.entity.Adv;
import com.example.week8.enums.AccountRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class AccountReqDto {

    private String name;

    private String password;

    private String role;

    @Builder
    public AccountReqDto(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    //String -> Enum
    public AccountRoleEnum toEnum(){
        return AccountRoleEnum.valueOf(role);
    }

    //dto->entity
    public Adv toAdv(){
        return Adv.builder()
                .name(name)
                .password(password)
                .role(AccountRoleEnum.valueOf(role))
                .adIngActYn(1)
                .balance(1000000L)
                .eventMoneyBalance(0L)
                .dayLimitBudget(0L)
                .build();
    }
    public Admin toAdmin(){
        return Admin.builder()
                .name(name)
                .password(password)
                .role(AccountRoleEnum.valueOf(role))
                .build();
    }

    //패스워드 인코드
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

}
