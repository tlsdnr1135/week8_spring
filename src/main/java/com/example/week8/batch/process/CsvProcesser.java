package com.example.week8.batch.process;

import com.example.week8.entity.csv.DadReportCsv;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@StepScope
@Configuration
public class CsvProcesser implements ItemProcessor<DadReportCsv, DadReportCsv> {


//    @Value("#{jobParameters[date]}")
//    private String date;

    @Override
    public DadReportCsv process( DadReportCsv item) throws Exception {
        System.out.print("여기가 배치 프로세서 : ");
        System.out.println(item.getRequestDate());
        return item;
    }
}
