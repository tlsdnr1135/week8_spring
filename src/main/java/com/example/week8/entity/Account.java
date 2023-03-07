package com.example.week8.entity;

import com.example.week8.enums.AccountRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

//TODO 테이블 이름 대문자????
//TODO 테이블 마다 nullable등 valid
@Entity(name = "MEMBER")
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {

    @Id
    @Column(name = "MEMBER_ID")
    private String name;

    @Column(name = "PWD")
    private String password; //패스워드

    @Column(name = "ROLE_GROUP")
    @Enumerated(value = EnumType.STRING)
    private AccountRoleEnum role; //역할그룹

//    @Builder
    public Account(String name, String password, AccountRoleEnum role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    //패스워드 인코드
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

}
