package com.example.week8.batch.job;

import com.example.week8.batch.exception.FileVerificationSkipper;
import com.example.week8.batch.listener.CsvJobListener;
import com.example.week8.batch.listener.CsvReaderListener;
import com.example.week8.batch.process.CsvProcesser;
import com.example.week8.batch.writer.CustomItemWriter;
import com.example.week8.entity.csv.DadReportCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CsvChunkJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CsvProcesser csvProcesser;
    private final CustomItemWriter customItemWriter;
    private final CsvJobListener csvJobListener;
    private final FlatFileItemReader itemReader;
    private final FileVerificationSkipper fileVerificationSkipper;

    private final CsvReaderListener csvReaderListener;



    @Bean
    public Job CSVJob()  {
        return jobBuilderFactory.get("csvJob")
                .start(step1())
                .listener(csvJobListener)
                .build();
    }

    //오류 익셉션 : 헤더가 틀리면.. 핸들러로 이동.
    //오류 익셉션 : 행

    @JobScope
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<DadReportCsv, DadReportCsv>chunk(3)
                .reader(itemReader)
                .faultTolerant().skipLimit(100)
                .skip(Exception.class)
                .skipPolicy(fileVerificationSkipper)
                .processor(csvProcesser)
                .writer(customItemWriter)
                .build();
    }

}