package com.example.week8.entity;

import com.example.week8.batch.job.CsvChunkJobConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    //배치 시작 후 상태 변경
    public void changeStatusING(){
        LocalDateTime localDateTime = LocalDateTime.now();
        this.taskStatus="ING";
        this.taskStartTime = localDateTime;
        System.out.println(id +":"+ taskStatus);
    }
    //배치 시작 후 상태 변경
    public void changeStatusCOMPLETE(){
        LocalDateTime localDateTime = LocalDateTime.now();
        this.taskStatus="COMPLETE";
        this.taskEndTime =  localDateTime;
        System.out.println(id +":"+ taskStatus);
    }
    //배치 시작 후 상태 변경
    public void changeStatusFAILED(){
        LocalDateTime localDateTime = LocalDateTime.now();
        this.taskStatus="ERROR";
        this.taskEndTime =  localDateTime;
        System.out.println(id +":"+ taskStatus);
    }

    //파라미터 만들기
    public JobParameters makeJobParam(){
        return new JobParametersBuilder().addString("taskName", String.valueOf(this.taskName)).toJobParameters();
    }
    
    //잡 실행 시키기
    public void runJob(CsvChunkJobConfig csvChunkJobConfig, JobLauncher jobLauncher, JobParameters jobParameters) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        jobLauncher.run(csvChunkJobConfig.CSVJob(), jobParameters);
    }
}
