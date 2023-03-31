package com.example.week8.entity;

import com.example.week8.enums.AccountRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity(name = "ADMIN")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "ADMIN_ID")
public class Admin extends Account{

    @CreatedDate
    @Column(name = "REG_TIME")
    private LocalDateTime temp;

    @Builder
    public Admin(String name, String password, AccountRoleEnum role, LocalDateTime temp) {
        super(name, password, role);
        this.temp = temp;
    }
}
