package com.example.week8.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 상속했을 때, 컬럼으로 인식하게 합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
@Getter // Getter가 있어야 jackson을 사용하여 객체를 반환할 때 같이 반환된다
public class Timestamped { // abstract는 상속으로만 사용할 수 있다

//    @CreatedDate // 생성일자임을 나타냅니다.
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;

//    @LastModifiedDate // 마지막 수정일자임을 나타냅니다.
//    @Column(name = "modified_at")
//    private LocalDateTime modifiedAt;

}
