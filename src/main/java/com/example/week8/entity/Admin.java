package com.example.week8.entity;

import com.example.week8.enums.AccountRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "ADMIN")
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "ADMIN_ID")
public class Admin extends Account{

    @CreatedDate
    @Column(name = "REG_TIME")
    private String temp;

    @Builder
    public Admin(String name, String password, AccountRoleEnum role, String temp) {
        super(name, password, role);
        this.temp = temp;
    }
}
