package com.example.week8.entity;

import lombok.Getter;

import javax.persistence.*;

//TODO 테이블 이름 대문자????
@Entity(name = "MEMBER")
@Getter
public class Account {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @Column(name = "PWD")
    private String password; //패스워드

    @Column(name = "ROLE_GROUP")
    private String roleGroup; //역할그룹

}
