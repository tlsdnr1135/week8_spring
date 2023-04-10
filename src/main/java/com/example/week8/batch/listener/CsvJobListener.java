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
        System.out.println("비포 잡 파라미터 " + jobExecution.getJobParameters());
        System.out.println("비포 잡 파라미터 " + jobExecution.getJobParameters().getString("taskName"));
        TaskRequest byTaskName = taskRequestRepository.findByTaskName(jobExecution.getJobParameters().getString("taskName"));
        byTaskName.changeStatusING();
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        //잡 컨텍스트에다 값 넣기.
        System.out.println("afterJobafterJobafterJobafterJobafterJobafterJobafterJobafterJobafterJob");
        System.out.println("잡 익스큐션 ");
        System.out.println(jobExecution);
        System.out.println("이걸로 체크 " + jobExecution.getStatus());
        System.out.println(jobExecution.getExecutionContext());
        System.out.println(jobExecution.getExecutionContext().get("taskName"));
        System.out.println();

        TaskRequest byTaskName = taskRequestRepository.findByTaskName(jobExecution.getJobParameters().getString("taskName"));
        if(String.valueOf(jobExecution.getStatus()).equals("FAILED")){
            System.out.println("들어오긴 하니!???");
            System.out.println("들어오긴 하니!???");
            System.out.println("들어오긴 하니!???");
            System.out.println("들어오긴 하니!???");
            System.out.println("들어오긴 하니!???");
            byTaskName.changeStatusFAILED();
        }else {
            byTaskName.changeStatusCOMPLETE();
        }


    }


}
