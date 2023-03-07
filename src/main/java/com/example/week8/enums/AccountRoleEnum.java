package com.example.week8.enums;

import lombok.Getter;

@Getter
public enum AccountRoleEnum {

    ROLE_ADV("ROLE_ADV"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String accountRole;

    AccountRoleEnum(String roleAdmin) {
        this.accountRole = roleAdmin;
    }

}