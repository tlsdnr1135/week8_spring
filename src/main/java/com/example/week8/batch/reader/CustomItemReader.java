package com.example.week8.batch.reader;

import com.example.week8.entity.csv.DadReportCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomItemReader {

//    @Value("#{jobParameters[taskName]}")
//    private String taskName;
    private final CustomLineCallbackHandle lineCallbackHandle;

    @Bean
    @StepScope
    public FlatFileItemReader<DadReportCsv> read(@Value("#{jobParameters[taskName]}") String taskName){
        PathResource pathResource = new PathResource("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/" + taskName + ".csv");
        
        //여기서 널 체크하기...
//        pathResource



        System.out.println("-------------------------------- 여기가 아이템 리더 --------------------------------------------");
        System.out.println("잡 파라미터 : " + taskName);
        try{
            FlatFileItemReader<DadReportCsv> build = new FlatFileItemReaderBuilder<DadReportCsv>()
                    .name("DadReportCsv")
//                    .resource(new PathResource("C:\\Users\\dev\\inteliJWorkspace\\2월\\week8\\src\\main\\resources\\file/"+taskName+".csv"))
                    .resource(new PathResource("C:\\java_workspace\\IntelliJ_workspace\\11h11m\\week8_spring\\src\\main\\resources\\file/"+taskName+".csv"))
                    .fieldSetMapper(new BeanWrapperFieldSetMapper<>())
                    .targetType(DadReportCsv.class)
                    .linesToSkip(1)
                    .encoding("utf-8")
                    .skippedLinesCallback(lineCallbackHandle)
                    .strict(true)
                    .delimited()
                    .names("requestDate", "dadDetId", "showCount", "clickCount", "avgShowRank", "avgCpc", "adCost")
                    .build();
            return build;
        }catch (Exception e){
            System.out.println(e);
            System.out.println("여기서 에러 리더 -----------------------------------------------------");
        }

        return null;
    }
}
