package com.example.week8.batch.listener;

import com.example.week8.entity.TaskRequest;
import com.example.week8.repository.TaskRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvJobListener implements JobExecutionListener {
    private final TaskRequestRepository taskRequestRepository;


    @Override
    @Transactional
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("BeforeJobBeforeJobBeforeJobBeforeJobBeforeJobBeforeJobBeforeJobBeforeJob");
        System.out.println("비포 잡 파라미터 " + jobExecution.getJobParameters().getString("taskPath"));
        TaskRequest byTaskName = taskRequestRepository.findByTaskPath(jobExecution.getJobParameters().getString("taskPath"));
        byTaskName.changeStatusING();
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        //잡 컨텍스트에다 값 넣기.
        System.out.println("afterJobafterJobafterJobafterJobafterJobafterJobafterJobafterJobafterJob");
        System.out.println("잡 스테이터스 " + jobExecution.getStatus());
        System.out.println(jobExecution.getExecutionContext().get("taskPath"));

        TaskRequest byTaskName = taskRequestRepository.findByTaskPath(jobExecution.getJobParameters().getString("taskPath"));
        if(String.valueOf(jobExecution.getStatus()).equals("FAILED")){
            byTaskName.changeStatusFAILED();
        }else {
            byTaskName.changeStatusCOMPLETE();
        }
    }


}
