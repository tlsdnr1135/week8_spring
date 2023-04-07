package com.example.week8.batch.reader;

import com.example.week8.entity.csv.DadReportCsv;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomItemReader {

//    @Value("#{jobParameters[taskName]}")
//    private String taskName;

    @Bean
    @StepScope
    public FlatFileItemReader<DadReportCsv> read(@Value("#{jobParameters[taskName]}") String taskName){


        System.out.println("여기가 아이템 리더");
        System.out.println("잡 파라미터 " + taskName);

        FlatFileItemReader<DadReportCsv> build = new FlatFileItemReaderBuilder<DadReportCsv>()
                .name("DadReportCsv")
                .resource(new PathResource("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/"+taskName+".csv"))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                .targetType(DadReportCsv.class)
                .linesToSkip(1)
//                .skippedLinesCallback()
                .strict(true)
//                .saveState(false)
                .delimited()
                .names("requestDate", "dadDetId", "showCount", "clickCount", "avgShowRank", "avgCpc", "adCost")
                .build();
        return build;
    }

}
