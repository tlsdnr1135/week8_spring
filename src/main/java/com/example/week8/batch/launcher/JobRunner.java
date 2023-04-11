package com.example.week8.batch.launcher;


import com.example.week8.batch.job.CsvChunkJobConfig;
import com.example.week8.entity.TaskRequest;
import com.example.week8.repository.TaskRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobRunner{

    private final JobLauncher jobLauncher;
    private final CsvChunkJobConfig csvChunkJobConfig;

    private final TaskRequestRepository taskRequestRepository;

    private final EntityManager entityManager;


    @Scheduled(fixedRate=30000) //30초 마다
    public void runJob() throws Exception {
        //Req리스트 가져오기
        List<TaskRequest> taskRequests = taskRequestRepository.findByTaskStatus("REQ");
        taskRequests.forEach(s -> {
            try {
                s.runJob(csvChunkJobConfig,jobLauncher,s.makeJobParam());
            } catch (JobInstanceAlreadyCompleteException e) {
                System.out.println("익셉션");
                throw new RuntimeException(e);
            } catch (JobExecutionAlreadyRunningException e) {
                System.out.println("익셉션");
                throw new RuntimeException(e);
            } catch (JobParametersInvalidException e) {
                System.out.println("익셉션");
                throw new RuntimeException(e);
            } catch (JobRestartException e) {
                System.out.println("익셉션");
                throw new RuntimeException(e);
            }
        });
    }

}
