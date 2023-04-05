package com.example.week8.batch.launcher;


import com.example.week8.batch.job.CsvChunkJobConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JobRunner{

    private final JobLauncher jobLauncher;
    private final CsvChunkJobConfig csvChunkJobConfig;

    @Scheduled(fixedRate=10000)
    public void runJob() throws Exception {
        //여기서 REQ 목록 가져오기

        //목록들 잡 돌리기...

        //






        System.out.println("여기는 메인 잡 러너");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.format(new Date());
        Date  date = new Date();
        //TODO 물어보기 데이트형식으로 어캐하나?
        JobParameters jobParameters = new JobParametersBuilder().addString("date", String.valueOf(date)).toJobParameters();
        jobLauncher.run(csvChunkJobConfig.CSVJob(),jobParameters);
    }
}
