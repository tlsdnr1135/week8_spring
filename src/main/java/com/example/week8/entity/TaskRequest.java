package com.example.week8.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class TaskRequest extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String requestDate; //기준 날짜 - 작업 요청한 날짜

    @ManyToOne
    @JoinColumn(name = "ADMIN_ID")
    private Admin admin; //회원 - 작업 요청한 회원 ID

    private String taskStatus; //태스크 상태 - REQ

    @Column(nullable = false,unique = true)
    private String taskName; //태스트 명 - 작업 명
    private String taskPath; //태스크 요청 파일 경로 - 업로드한 파일 경로

    @CreatedDate
    private LocalDateTime taskRequestTime; //태스크 요청 시간 - 작업 요청한 날짜 및 시간
    private LocalDateTime taskStartTime; //태스크 시작 시간 - null
    private LocalDateTime taskEndTime; //태스크 종료 시간 - null

    @Builder
    public TaskRequest(Long id, String requestDate, Admin admin, String taskStatus, String taskName, String taskPath, LocalDateTime taskRequestTime, LocalDateTime taskStartTime, LocalDateTime taskEndTime) {
        this.id = id;
        this.requestDate = requestDate;
        this.admin = admin;
        this.taskStatus = taskStatus;
        this.taskName = taskName;
        this.taskPath = taskPath;
        this.taskRequestTime = taskRequestTime;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }
}
