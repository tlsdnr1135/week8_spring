package com.example.week8.batch.job;

import com.example.week8.batch.listener.CsvJobListener;
import com.example.week8.batch.process.CsvProcesser;
import com.example.week8.batch.reader.CustomItemReader;
import com.example.week8.batch.writer.CustomItemWriter;
import com.example.week8.entity.csv.DadReportCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class CsvChunkJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CsvProcesser csvProcesser;
    private final CustomItemWriter customItemWriter;
    private final CsvJobListener csvJobListener;
    private final FlatFileItemReader itemReader;
    private final CustomItemReader customItemReader;


    @Bean
    public Job CSVJob()  {
        return jobBuilderFactory.get("csvJob")
                .start(step1())
                .listener(csvJobListener)
                .build();
    }

    @JobScope
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<DadReportCsv, DadReportCsv>chunk(3)
                .reader(itemReader)
                .processor(csvProcesser)
                .writer(customItemWriter)
//                .listener(csvWriteListener)
                .build();
    }

}
